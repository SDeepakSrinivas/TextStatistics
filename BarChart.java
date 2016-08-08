import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class BarChart extends ApplicationFrame
{
   public BarChart( String applicationTitle , String chartTitle, int avgSentLengthLetters, int avgSentLengthWords, int countPunc, int countPara)
   {
      super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Category",            
         "Score",            
         createDataset(avgSentLengthLetters, avgSentLengthWords, countPunc,countPara),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
      setContentPane( chartPanel ); 
   }
   private CategoryDataset createDataset(int avgSentLengthLetters,int avgSentLengthWords,int countPunc,int countPara)
   {
             
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  

      dataset.addValue( avgSentLengthLetters , "War and Peace" , "Avg Letters in a sentence" );        
      dataset.addValue( avgSentLengthWords , "War and Peace" , "Avg Words in a sentence" ); 
      dataset.addValue( countPunc , "War and Peace" , "No. of Punctuation marks" ); 
      dataset.addValue( countPara , "War and Peace" , "No. of Paragraphs" ); 
      
      return dataset; 
   }
   
}