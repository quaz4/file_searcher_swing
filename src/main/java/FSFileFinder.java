// package edu.curtin.comp3003.filesearcher;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FSFileFinder implements Runnable
{
    private String searchPath;
    private FSUserInterface ui;
    
    private FSFilter filter;

    public FSFileFinder(String searchPath, FSUserInterface ui, FSFilter filter)
    {
        this.searchPath = searchPath;   
        this.ui = ui;
        this.filter = filter;
    }
    
    @Override
    public void run()
    {
        try
        {
            // Recurse through the directory tree
            Files.walkFileTree(Paths.get(searchPath), new SimpleFileVisitor<Path>()
            { 
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                {
                    filter.add(file.toString());
                    
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        catch(IOException e)
        {
            // This error handling is a bit quick-and-dirty, but it will suffice here.
            ui.showError(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
