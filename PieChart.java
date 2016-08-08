import java.util.HashMap;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
 
public class PieChart extends ApplicationFrame 
{
   public PieChart( String title,HashMap<String,Integer> hash) 
   {
      super( title ); 
      setContentPane(createDemoPanel(hash));
   }
   private static PieDataset createDataset(HashMap<String,Integer> hash) 
   {
      DefaultPieDataset dataset = new DefaultPieDataset( );
      for(String key : hash.keySet())
    	  dataset.setValue(key, hash.get(key));
      return dataset;         
   }
   private static JFreeChart createChart( PieDataset dataset )
   {
      JFreeChart chart = ChartFactory.createPieChart(      
         "Most Frequent Words",  // chart title 
         dataset,        // data    
         true,           // include legend   
         true, 
         false);

      return chart;
   }
   public static JPanel createDemoPanel(HashMap<String, Integer> hash)
   {
      JFreeChart chart = createChart(createDataset(hash) );  
      return new ChartPanel( chart ); 
   }

}