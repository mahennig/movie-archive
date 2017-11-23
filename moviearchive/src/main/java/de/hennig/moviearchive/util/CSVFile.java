package de.hennig.moviearchive.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CSVFile {

    private final String FILE_EXTENSION = ".csv";
    private final String SEPARATOR = ",";
    private final String END_OF_LINE = "\n";
    private File file;
    private String fileName;
    private FileWriter writer;
    private BufferedReader reader;

    public CSVFile(String fileName) {
        this.fileName = fileName;
    }

    public CSVFile() {
        this.fileName = null;
    }

    public CSVFile(File file) {
        file.deleteOnExit();
        this.file = file;
    }

    public void write(List<String> values) throws IOException {

        for (int i = 0; i < values.size(); i++) {
            Object value = values.get(i);
            if (i > 0) {
                getWriter().append(SEPARATOR);
            }
            if (value != null)
                getWriter().append(value.toString());
        }
        getWriter().append(END_OF_LINE);
    }

    public void write(String value) throws IOException {
        List<String> values = new ArrayList<String>();
        values.add(value);
        this.write(values);
    }

    public List<String> readLine() throws IOException {
        final String line = getReader().readLine();
        if (line != null) {
            return Arrays.asList(line.split(SEPARATOR));
        }

        return null;
    }

    public List<String> readAllLines() throws IOException {
        List<String> lines = new ArrayList<>();
        String line = getReader().readLine();

        while (line != null) {
            lines.addAll(Arrays.asList(line.split(SEPARATOR)));
            line = getReader().readLine();
        }

        return lines;
    }

    public void close() throws IOException {
        getWriter().flush();
        getWriter().close();
        getReader().close();
        writer = null;
        reader = null;
    }

    public File getFile() throws IOException {
        if (file == null) {
            file = File.createTempFile(fileName, FILE_EXTENSION);
            file.deleteOnExit();
        }
        return file;
    }

    private FileWriter getWriter() throws IOException {
        if (writer == null) {
            writer = new FileWriter(getFile());
        }

        return writer;
    }

    private BufferedReader getReader() throws IOException {
        if (reader == null) {
            reader = new BufferedReader(new FileReader(getFile()));
        }

        return reader;
    }

    public Collection<String> readFromFile(File sourcePath, String uniqueId, int uniqueIdIndex) {
        BufferedReader reader = null;
        String line;
        try {
            reader = new BufferedReader(new FileReader(sourcePath));
            while ((line = reader.readLine()) != null) {
                String[] items = line.split(SEPARATOR);
                if (items.length > 0 && uniqueId.equalsIgnoreCase(items[uniqueIdIndex])) {
                    return Arrays.asList(items);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Collections.EMPTY_LIST;
    }
    public Collection<String>  readFromFile(File sourcePath, String firstUniqueValue,int firstUniqueIndex,String secondUniqueValue,int
            secondUniqueIndex) {
        BufferedReader reader = null;
        String line;
        try {
            reader = new BufferedReader(new FileReader(sourcePath));
            while ((line = reader.readLine()) != null) {
                String[] items = line.split(SEPARATOR);
                if (items.length > 0 && firstUniqueValue.equalsIgnoreCase(items[firstUniqueIndex]) &&  secondUniqueValue.equalsIgnoreCase(items[secondUniqueIndex])) {
                    return Arrays.asList(items);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Collections.EMPTY_LIST;
    }
}
