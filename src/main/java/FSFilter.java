import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class FSFilter implements Runnable
{
    private ExecutorService es;

    private FSUserInterface ui;
    private String search;

    public FSFilter(FSUserInterface ui, String search)
    {
        this.ui = ui;
        this.search = search;
        this.es = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
        );
    }

    @Override
    public void run()
    {
        while(true)
        {
            // String next = this.remove();

            // // If the search term is found in the string, add it to the ui
            // if (next.contains(this.search))
            // {
            //     this.ui.addResult(next);
            // }
        }
    }

    public void add(String fileName)
    {
        this.es.submit(() ->
        {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                   if (line.contains(this.search))
                   {
                        this.ui.addResult(fileName);
                   }
                }
            }
            catch(FileNotFoundException e)
            {

            }
            catch(IOException e)
            {

            }
        });
    }
}