package net.minecraft.server;

public enum EnumItemSlot {

    MAINHAND(EnumItemSlot.Function.HAND, 0, 0, "mainhand"),
    OFFHAND(EnumItemSlot.Function.HAND, 1, 5, "offhand"),
    FEET(EnumItemSlot.Function.ARMOR, 0, 1, "feet"),
    LEGS(EnumItemSlot.Function.ARMOR, 1, 2, "legs"),
    CHEST(EnumItemSlot.Function.ARMOR, 2, 3, "chest"),
    HEAD(EnumItemSlot.Function.ARMOR, 3, 4, "head"),
    TROPHY_1(EnumItemSlot.Function.TROPHY, 0, 6, "trophy_1"),
    TROPHY_2(EnumItemSlot.Function.TROPHY, 1, 7, "trophy_2"),
    TROPHY_3(EnumItemSlot.Function.TROPHY, 2, 8, "trophy_3");

    private final EnumItemSlot.Function g;
    private final int h;
    private final int i;
    private final String j;

    private EnumItemSlot(EnumItemSlot.Function enumitemslot_function, int i, int j, String s) {
        this.g = enumitemslot_function;
        this.h = i;
        this.i = j;
        this.j = s;
    }

    public EnumItemSlot.Function a() {
        return this.g;
    }

    public int b() {
        return this.h;
    }

    public int c() {
        return this.i;
    }

    public String d() {
        return this.j;
    }

    public static EnumItemSlot a(String s) {
        EnumItemSlot[] aenumitemslot = values();
        int i = aenumitemslot.length;

        for (int j = 0; j < i; ++j) {
            EnumItemSlot enumitemslot = aenumitemslot[j];

            if (enumitemslot.d().equals(s)) {
                return enumitemslot;
            }
        }

        throw new IllegalArgumentException("Invalid slot \'" + s + "\'");
    }

    public static enum Function {

        HAND,
        ARMOR,
        TROPHY;

        private Function() {}
    }
}
