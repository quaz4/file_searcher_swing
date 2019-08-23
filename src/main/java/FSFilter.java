import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FSFilter implements Runnable
{
    private BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    private FSUserInterface ui;
    private String search;

    public FSFilter(FSUserInterface ui, String search)
    {
        this.ui = ui;
        this.search = search;
    }

    @Override
    public void run()
    {
        try
        {
            while(true)
            {
                String next = this.remove();

                // If the search term is found in the string, add it to the ui
                if (next.contains(this.search))
                {
                    this.ui.addResult(next);
                }
            }
        }
        catch (InterruptedException e)
        {
            // Do nothing and let the thread end
        }
    }

    public void add(String fileName)
    {
        queue.add(fileName);
    }

    private String remove() throws InterruptedException
    {
        return queue.take();
    }
}