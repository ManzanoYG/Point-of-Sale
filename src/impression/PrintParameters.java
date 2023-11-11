package impression;

import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrinterResolution;

/**
 *
 *
 */
public class PrintParameters {
   /**
    *  Les dimensions de la feuille de papier en inches
    */
   private Rectangle2D.Double paperArea;
   
   /**
    * Les marges imprimables en inches
    */
   private Rectangle2D.Double printableArea;
   
   /**
    *  La résolution en DPI
    */
   private int DPI;
   
   /**
    * L'orientation du papier
    */
   private OrientationRequested orientation;
   
   /**
    * Les attributs de la requête d'impression courante
    */
   private PrintRequestAttributeSet attributes;
   
   /**
    * Le service d'impression (ou imprimante) sélectionné
    */
   private PrintService printService;
   
   /**
    *  Indique si l'impression est en couleur ou non
    */
   private boolean monochrom;
   
   public PrintParameters(){
      this(new HashPrintRequestAttributeSet(), PrintServiceLookup.lookupDefaultPrintService());
   }
   
   public PrintParameters(PrintRequestAttributeSet attributesSet, PrintService printService){
      try {
         this.attributes = attributesSet;
         this.printService = printService;
         
         // Récupère la résolution
         PrinterResolution res = (PrinterResolution) attributesSet.get(PrinterResolution.class);
         if (res == null) {
            res = (PrinterResolution) printService.getDefaultAttributeValue(PrinterResolution.class);
         }
         DPI = res.getResolution(PrinterResolution.DPI)[0];
         
         // Récupère l'orientation
         orientation = (OrientationRequested)attributesSet.get(OrientationRequested.class);
         if (orientation == null){
            orientation = (OrientationRequested)printService.getDefaultAttributeValue(OrientationRequested.class);
         }
         
         // Détermine les dimensions de la feuille et des marges
         PrinterJob printerJob = PrinterJob.getPrinterJob();
         printerJob.setPrintService(printService);
         
         // Récupère les marges physiques de l'imprimante
         float [] physicalMargin;
         MediaPrintableArea printableZone = (MediaPrintableArea)attributesSet.get(MediaPrintableArea.class);
         if (printableZone == null){
            printableZone = (MediaPrintableArea)printService.getDefaultAttributeValue(MediaPrintableArea.class);
            
         }
         
         PageFormat pageFormat = printerJob.getPageFormat(attributesSet);
         
         double paperWidth = pageFormat.getWidth() / 72.0;
         double paperHeight = pageFormat.getHeight() / 72.0;
         if (printableZone == null){
            physicalMargin = new float[4];
            physicalMargin[0] = 0.0f;
            physicalMargin[1] = 0.0f;
            physicalMargin[2] = (float)paperWidth;
            physicalMargin[3] = (float)paperHeight;
         } else {
            physicalMargin = printableZone.getPrintableArea(MediaPrintableArea.INCH);
         }
         if (orientation.equals(OrientationRequested.LANDSCAPE) ||orientation.equals(OrientationRequested.REVERSE_LANDSCAPE)){
            // Inversion des marges physiques
            float temp = physicalMargin[0];
            physicalMargin[0] = physicalMargin[1];
            physicalMargin[1] = temp;
            temp = physicalMargin[2];
            physicalMargin[2] = physicalMargin[3];
            physicalMargin[3] = temp;
         }
         
         // Calcul des différences
         physicalMargin[2] = (float)(paperWidth - (physicalMargin[2] + physicalMargin[0]));
         physicalMargin[3] = (float)(paperHeight - (physicalMargin[3] + physicalMargin[1]));
         
         double xMargin = (pageFormat.getImageableX() / 72.0) + physicalMargin[0];
         double yMargin = (pageFormat.getImageableY() / 72.0) + physicalMargin[1];
         double imageWidth = (pageFormat.getImageableWidth() / 72.0) - (physicalMargin[0] + physicalMargin[2]);
         double imageHeight = (pageFormat.getImageableHeight() / 72.0) - (physicalMargin[1] + physicalMargin[3]);
         
         paperArea = new Rectangle2D.Double(0.0, 0.0, paperWidth, paperHeight);
         printableArea = new Rectangle2D.Double(xMargin, yMargin, imageWidth, imageHeight);
         
         
         // La gestion de la couleur
         Chromaticity chromaticity = (Chromaticity) attributesSet.get(Chromaticity.class);
         if (chromaticity == null) {
            chromaticity = (Chromaticity) printService.getDefaultAttributeValue(Chromaticity.class);
         }
         monochrom = chromaticity.equals(Chromaticity.MONOCHROME);
      } catch (PrinterException ex) {
         Logger.getLogger(PrintParameters.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
   
   /**
    *  Retourne les dimensions de la feuille de papier en inches
    *
    *  @return les dimensions de la feuille en inches
    */
   public Rectangle2D.Double getPaperArea() {
      return paperArea;
   }
   
   /**
    *  Retourne la zone imprimable en inches
    * @return la zone imprimable en inches
    */
   public Rectangle2D.Double getPrintableArea() {
      return printableArea;
   }
   
   /**
    * Retourne la résolution de l'impression en DPI
    * @return la résolution de l'impression en DPI
    */
   public int getDPI() {
      return DPI;
   }
   
   /**
    *  Indique si l'impression est en couleur ou monochrome
    * @return true si l'impression est monochrome
    */
   public boolean isMonochrom() {
      return monochrom;
   }
   
   /**
    * Retourne l'orientation de la feuille
    * @return l'orientation de la feuille
    */
   public OrientationRequested getOrientation() {
      return orientation;
   }
   
   /**
    * Retourne les requêtes d'impression courantes
    *
    * @return  les requêtes d'impression courantes
    */
   protected PrintRequestAttributeSet getAttributes() {
      return attributes;
   }
   
   /**
    * Retourne le service d'impression sélectionné
    *
    * @return  le service d'impression sélectionné
    */
   protected PrintService getPrintService() {
      return printService;
   }
}