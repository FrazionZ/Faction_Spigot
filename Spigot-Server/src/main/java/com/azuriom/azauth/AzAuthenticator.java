package com.azuriom.azauth;


import com.azuriom.azauth.gson.InstantAdapter;
import com.azuriom.azauth.gson.UuidAdapter;
import com.azuriom.azauth.model.User;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.Blocking;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

public class AzAuthenticator {

    private static final Logger LOGGER = Logger.getLogger(AzAuthenticator.class.getName());

    private static final Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(Instant.class, new InstantAdapter())
            .registerTypeAdapter(UUID.class, new UuidAdapter())
            .create();

    private final String url;

    /**
     * Construct a new AzAuthenticator instance.
     *
     * @param url the website url
     */
    public AzAuthenticator(@NotNull String url) {
        this.url = Objects.requireNonNull(url, "url");
        if (!url.startsWith("https://")) {
            LOGGER.warning("HTTP links are not secure, use HTTPS instead.");
        }
    }

    /**
     * Gets the website url.
     *
     * @return the website url
     */
    public @NotNull String getUrl() {
        return this.url;
    }

    /**
     * Try to authenticate the player on the website and get his profile.
     *
     * @param email    the player email
     * @param password the player password
     * @return the player profile
     * @throws AuthenticationException if credentials are not valid
     * @throws IOException             if an IO exception occurs
     */
    @Blocking
    public @NotNull User authenticate(@NotNull String email, @NotNull String password)
            throws AuthenticationException, IOException {
        return this.authenticate(email, password, User.class);
    }

    /**
     * Try to authenticate the player on the website and get his profile with a given response type.
     *
     * @param email        the player email
     * @param password     the player password
     * @param responseType the class of the response
     * @param <T>          the type of the response
     * @return the player profile
     * @throws AuthenticationException if credentials are not valid
     * @throws IOException             if an IO exception occurs
     */
    @Blocking
    public <T> @NotNull T authenticate(@NotNull String email, @NotNull String password, @NotNull Class<T> responseType)
            throws AuthenticationException, IOException {
        JsonObject body = new JsonObject();
        body.addProperty("email", email);
        body.addProperty("password", password);

        return this.post("authenticate", body, responseType);
    }

    /**
     * Verify an access token and get the associated profile.
     *
     * @param accessToken the player access token
     * @return the player profile
     * @throws AuthenticationException if credentials are not valid
     * @throws IOException             if an IO exception occurs
     */
    @Blocking
    public @NotNull User verify(@NotNull String accessToken, @NotNull String ipAddress) throws AuthenticationException, IOException {
        return this.verify(accessToken, ipAddress, User.class);
    }

    @Blocking
    public <T> @NotNull T persocode(@NotNull String accessToken, String pcode, @NotNull Class<T> responseType)
            throws AuthenticationException, IOException {
        JsonObject body = new JsonObject();
        body.addProperty("access_token", accessToken);
        body.addProperty("pcode", pcode);
        return this.post("persocode", body, responseType);
    }

    /**
     * Verify an access token and get the associated profile with a given response type.
     *
     * @param accessToken  the player access token
     * @param responseType the class of the response
     * @param <T>          the type of the response
     * @return the player profile
     * @throws AuthenticationException if credentials are not valid
     * @throws IOException             if an IO exception occurs
     */
    @Blocking
    public <T> @NotNull T verify(@NotNull String accessToken, String ipAddress, @NotNull Class<T> responseType)
            throws AuthenticationException, IOException {
        JsonObject body = new JsonObject();
        body.addProperty("access_token", accessToken);
        body.addProperty("ipAddress", ipAddress);
        return this.post("verify", body, responseType);
    }

    /**
     * Invalidate the given access token.
     * To get a new valid access token you need to use {@link #authenticate(String, String)} again.
     *
     * @param accessToken the player access token
     * @throws AuthenticationException if credentials are not valid
     * @throws IOException             if an IO exception occurs
     */
    @Blocking
    public void logout(@NotNull String accessToken) throws AuthenticationException, IOException {
        JsonObject body = new JsonObject();
        body.addProperty("access_token", accessToken);

        this.post("logout", body, null);
    }

    @Blocking
    @Contract("_, _, null -> null; _, _, !null -> !null")
    private <T> T post(@NotNull String endPoint, @NotNull JsonObject body, @Nullable Class<T> responseType) throws AuthenticationException, IllegalStateException, IOException {

        body.addProperty("client", "server");

        URL apiUrl = new URL(this.url + "/api/auth/v2/" + endPoint);
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.addRequestProperty("User-Agent", "AzAuth authenticator v1");
        connection.addRequestProperty("Content-Type", "application/json; charset=utf-8");

        try (OutputStream out = connection.getOutputStream()) {
            out.write(body.toString().getBytes(StandardCharsets.UTF_8));
        }

        if (connection.getResponseCode() == 422) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
                AuthResponse status = GSON.fromJson(reader, AuthResponse.class);

                throw new AuthenticationException(status.getMessage());
            }
        }

        if (responseType == null) {
            return null;
        }

        BufferedReader readerr = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = readerr.readLine()) != null) {
            result.append(line);
        }

        try {
            T response = GSON.fromJson(new JSONObject(result.toString()).getJSONObject("data").toString(), responseType);
            if (response == null) {
                throw new IllegalStateException("Invalid Credentials");
            }

            return response;
        } catch (JSONException e) {
            System.out.println(result.toString());
            throw new IllegalStateException("Internal Server Error "+result.toString());
        }


    }
}
