package net.minecraft.server.frazionz.recipes;

import com.google.common.collect.Maps;
import com.google.gson.*;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.frazionz.resources.JsonUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.*;
import java.util.Map.Entry;

public class AmelioratorRecipes {

    private final static Map<ItemStack[], ItemResult> RECIPES = Maps.newHashMap();
    private final static Logger LOGGER = LogManager.getLogger();

    static
    {
        parseJsonRecipes();
    }

    /**
     * Adds a forging recipe using an ItemStack as the input for the recipe.
     */
    public static void addRecipes(ItemStack[] items, ItemResult result)
    {
        RECIPES.put(items, result);
    }

    public static ItemStack getResult(ItemStack[] items)
    {
        Iterator<Entry<ItemStack[], ItemResult>> it = RECIPES.entrySet().iterator();



        return null;
    }

    public Map<ItemStack[], ItemResult> getRecipes()
    {
        return RECIPES;
    }

    private static boolean areItemStackEqual(ItemStack key1, ItemStack key2) {
        if(key1.isEmpty() && !key2.isEmpty()) return false;
        if(!key1.isEmpty() && key2.isEmpty()) return false;
        if(key1.getItem() != key2.getItem()) return false;
        if(key1.getData() != key2.getData()) return false;
        return true;

    }

    private static boolean parseJsonRecipes()
    {
        FileSystem filesystem = null;
        Gson gson = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
        boolean flag1;

        try
        {
            URL url = AmelioratorRecipes.class.getResource("/assets/.mcassetsroot");

            if (url != null)
            {
                URI uri = url.toURI();
                Path path;

                if ("file".equals(uri.getScheme()))
                {
                    path = Paths.get(AmelioratorRecipes.class.getResource("/assets/frazionz/custom_recipes/item_crusher").toURI());
                }
                else
                {
                    if (!"jar".equals(uri.getScheme()))
                    {
                        LOGGER.error("Unsupported scheme " + uri + " trying to list all recipes");
                        boolean flag2 = false;
                        return flag2;
                    }

                    filesystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
                    path = filesystem.getPath("/assets/frazionz/custom_recipes/item_crusher");
                }

                Iterator<Path> iterator = Files.walk(path).iterator();

                while (iterator.hasNext())
                {
                    Path path1 = iterator.next();

                    if ("json".equals(FilenameUtils.getExtension(path1.toString())))
                    {
                        Path path2 = path.relativize(path1);
                        String s = FilenameUtils.removeExtension(path2.toString()).replaceAll("\\\\", "/");
                        BufferedReader bufferedreader = null;

                        try
                        {
                            boolean flag;

                            try
                            {
                                bufferedreader = Files.newBufferedReader(path1);
                                parseRecipeJson((JsonObject) JsonUtils.fromJson(gson, bufferedreader, JsonObject.class));
                            }
                            catch (JsonParseException jsonparseexception)
                            {
                                LOGGER.error("Parsing error loading recipe " + s, (Throwable)jsonparseexception);
                                flag = false;
                                return flag;
                            }
                        }
                        finally
                        {
                            IOUtils.closeQuietly((Reader)bufferedreader);
                        }
                    }
                }

                return true;
            }

            LOGGER.error("Couldn't find .mcassetsroot");
            flag1 = false;
        }
        catch (IOException | URISyntaxException urisyntaxexception)
        {
            LOGGER.error("Couldn't get a list of all recipe files", (Throwable)urisyntaxexception);
            flag1 = false;
            return flag1;
        }
        finally
        {
            IOUtils.closeQuietly((Closeable)filesystem);
        }

        return flag1;
    }

    private static void parseRecipeJson(JsonObject json)
    {
        String itemName = JsonUtils.getString(json, "item");
        Item item = Item.b(itemName);
        if(item == null) {
            LOGGER.warn("Item: " + itemName + " doesn't exist.");
            return;
        }
        ItemResult result = new ItemResult(JsonUtils.getJsonArray(json, "results"));
        addRecipes(new ItemStack[]{ new ItemStack(item) }, result);
    }

    static class ItemResult {

        private CrushItem[] stacks;
        private Item result;

        public ItemResult(CrushItem[] stacks) {
            this.stacks = stacks;
        }

        public ItemResult(JsonArray json) {
            List<CrushItem> items = new ArrayList<>();
            for(int i = 0; i < json.size(); i++) {
                CrushItem item = new CrushItem(json.get(i).getAsJsonObject());
                if(item.isValid())
                    items.add(item);
            }
            this.stacks = new CrushItem[items.size()];
            for(int i = 0; i < items.size(); i++)
                stacks[i] = items.get(i);
        }

        public ItemStack[] getResult() {
            ItemStack[] stack = new ItemStack[stacks.length];
            for(int i = 0; i < stacks.length; i++)
                stack[i] = stacks[i].getItem();
            return stack;
        }
    }

    static class CrushItem {
        private Item item;
        private short itemDamage = 0;

        CrushItem(Item item) {
            this.item = item;
        }

        CrushItem(JsonObject json) {
            String itemName = JsonUtils.getString(json, "item");
            Item item = Item.b(itemName);
            if(item == null) {
                LOGGER.error("Item Result: " + itemName + " doesn't exist.");
            }
            this.item = item;

            if(json.has("meta"))
                itemDamage = json.get("meta").getAsShort();
        }

        public void setItemDamage(short itemDamage) {
            this.itemDamage = itemDamage;
        }

        ItemStack getItem() {
            return new ItemStack(item, 1, itemDamage);
        }

        boolean isValid() {
            return item != null;
        }
    }

}
