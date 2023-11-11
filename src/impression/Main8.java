package impression;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import sun.print.DialogTypeSelection;

public class Main8 extends JFrame{
   private PrintParameters printParameters = new PrintParameters();
   
   private PrintRectangleResTextPreview printableObject = new PrintRectangleResTextPreview();
   
   private PreviewPanel previewPanel = new PreviewPanel();
   
   /** Constructeur par défaut de Main8 */
   public Main8() {
      JPanel internalPanel = new JPanel();
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      internalPanel.setLayout(new BorderLayout());
      
      setTitle(printParameters.getPrintService().getName());
      
      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
      JButton configButton = new JButton("Configuration");
      configButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            PrinterJob job = PrinterJob.getPrinterJob();
            PrintRequestAttributeSet attribSet = printParameters.getAttributes();
//            attribSet.add(DialogTypeSelection.NATIVE);
            try {
               job.setPrintService(printParameters.getPrintService());
               if (job.printDialog(attribSet)){
                  printParameters = new PrintParameters(attribSet, job.getPrintService());
                  setTitle(printParameters.getPrintService().getName());
                  previewPanel.repaint();
               }
            } catch (PrinterException ex) {
               ex.printStackTrace();
            }
            
         }
      });
      buttonPanel.add(configButton);
      
      JButton printButton = new JButton("Imprimer");
      printButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               PrinterJob job = PrinterJob.getPrinterJob();
               job.setPrintable(printableObject);
               job.setPrintService(printParameters.getPrintService());
               job.print(printParameters.getAttributes());
            } catch (PrinterException ex) {
               ex.printStackTrace();
            }
         }
      });
      buttonPanel.add(printButton);
      
      internalPanel.add(buttonPanel, BorderLayout.SOUTH);
      
      internalPanel.add(previewPanel, BorderLayout.CENTER);
      
      setContentPane(internalPanel);
      pack();
      
   }
   
   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
      java.awt.EventQueue.invokeLater(new Runnable() {
         
         public void run() {
            new Main8().setVisible(true);
         }
      });
   }
   
   private class PreviewPanel extends JPanel{
      PreviewPanel(){
         addComponentListener(new ComponentListener() {
            public void componentHidden(ComponentEvent e) {
            }
            public void componentMoved(ComponentEvent e) {
            }
            public void componentResized(ComponentEvent e) {
               repaint();
            }
            public void componentShown(ComponentEvent e) {
               repaint();
            }
         });
         
         setPreferredSize(new Dimension(400,400));
      }
      public void paint(Graphics g) {
         Graphics2D g2d = (Graphics2D)g;
         
         Dimension size = getSize();
         
         printableObject.preview(g2d, size, printParameters);
      }
      
   }
}