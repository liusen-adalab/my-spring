package org.springframework.test.ioc;

import org.junit.Test;
import org.springframwork.core.io.*;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class ResourceAndResourceLoader {

    @Test
    public void testResourceLoader() throws IOException {
        DefaultResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource("classpath:hello.txt");
        InputStream inputStream = resource.getInputStream();
        assertNotNull(inputStream);
        assertEquals("hello world", new String(inputStream.readAllBytes()));

        resource = loader.getResource("src/test/resources/hello.txt");
        assertTrue(resource instanceof FileSystemResource);
        inputStream = resource.getInputStream();
        assertEquals("hello world", new String(inputStream.readAllBytes()));

        resource = loader.getResource("https://www.baidu.com");
        assertTrue(resource instanceof UrlResource);
        inputStream = resource.getInputStream();
        System.out.println(new String(inputStream.readAllBytes()));
    }
}
