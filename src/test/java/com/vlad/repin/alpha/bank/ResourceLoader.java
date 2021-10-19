package com.vlad.repin.alpha.bank;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vlad.repin.alpha.bank.exceptions.TestResourceLoadException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
@UtilityClass
public class ResourceLoader {

    public String loadFromResource(
        Class<?> testClass,
        String resourceName
    ) {
        try (InputStream is = testClass.getResourceAsStream(resourceName)) {
            return IOUtils.toString(is, StandardCharsets.UTF_8);
        } catch (IOException exc) {
            log.error(String.format("Failed to load contents from \"%s\".", resourceName), exc);
            throw new TestResourceLoadException("Failed to load contents from" + resourceName, exc);
        }
    }

    public byte[] loadFromResourceAsByteArray(
        Class<?> testClass,
        String resourceName
    ) {
        try (InputStream is = testClass.getResourceAsStream(resourceName)) {
            return IOUtils.toByteArray(is);
        } catch (IOException exc) {
            log.error(String.format("Failed to load contents from \"%s\".", resourceName), exc);
            throw new TestResourceLoadException("Failed to load contents from" + resourceName, exc);
        }
    }

    public <T> T loadFromJson(
        ObjectMapper objectMapper,
        Class<?> testClass,
        String resourceName,
        TypeReference<T> typeReference
    ) {
        String json = loadFromResource(testClass, resourceName);
        return converter(objectMapper, json, typeReference);
    }

    private <T> T converter(
        ObjectMapper objectMapper,
        String json,
        TypeReference<T> typeReference
    ) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (Exception exc) {
            throw new TestResourceLoadException("Failed to convert", exc);
        }
    }
}
