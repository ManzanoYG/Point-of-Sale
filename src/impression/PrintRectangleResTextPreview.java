package impression;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;


public class PrintRectangleResTextPreview implements Printable {
   
   // Résolution d'un contexte graphique Java
   private static final double JAVA_DPI = 72.0;
   
   // Police d'affichage du texte
   private Font font = new Font(Font.MONOSPACED, Font.BOLD, 16);
   
   // Résolution de l'impression
   private int resolution = 72;
   
   /** Constructeur par défaut de PrintRectangle */
   public PrintRectangleResTextPreview() {
   }
   
   /**
    * Méthode qui restaure le contexte graphique dans sa résolution réelle.
    *
    * @param graphics le contexte graphique
    * @param pageFormat information sur le format de la page
    * @return le rectangle de la zone imprimable dans la résolution réelle
    */
   protected Rectangle restoreRealDpi(Graphics2D graphics, PageFormat pageFormat){
      Rectangle retValue = new Rectangle();
      
      // Détermine la résolution réelle
      Rectangle deviceBounds = graphics.getDeviceConfiguration().getBounds();
      double pageWidth72Dpi  = pageFormat.getWidth();
      double pageHeight72Dpi = pageFormat.getHeight();
      
      double widthResolution  = (JAVA_DPI * deviceBounds.getWidth())/pageWidth72Dpi;
      double heightResolution = (JAVA_DPI * deviceBounds.getHeight())/pageHeight72Dpi;
      
      // Détermine la résolution pour l'affichage du texte
      resolution = (int)Math.round((widthResolution + heightResolution)/2.0);
      
      
      // Détermine les dimensions réelle de la zone imprimable
      double realImageableX      = (pageFormat.getImageableX()*widthResolution)/ JAVA_DPI;
      double realImageableWidth  = (pageFormat.getImageableWidth()*widthResolution)/ JAVA_DPI;
      double realImageableY      = (pageFormat.getImageableY()*heightResolution)/ JAVA_DPI;
      double realImageableHeight = (pageFormat.getImageableHeight()*heightResolution)/ JAVA_DPI;
      
      // Modifie la transformation du contexte graphique
      graphics.setTransform(new AffineTransform()); // Passe en résolution réelle
      
      switch (pageFormat.getOrientation()){
         case PageFormat.LANDSCAPE : {
            // Les marges retournées par pageFormat prennent en compte la rotation
            // Il faut les inverser
            double temp = realImageableX;
            realImageableX = realImageableY;
            realImageableY = temp;
            temp = realImageableWidth;
            realImageableWidth = realImageableHeight;
            realImageableHeight = temp;
            
            // Effectue la rotation
            graphics.rotate(-Math.PI / 2.0);
            
            // Translation pour s'aligner sur les marges
            graphics.translate(-realImageableWidth + realImageableX, realImageableY);
            break;
         }
         case PageFormat.REVERSE_LANDSCAPE : {
            // Les marges retournées par pageFormat prennent en compte la rotation
            // Il faut les inverser
            double temp = realImageableX;
            realImageableX = realImageableY;
            realImageableY = temp;
            temp = realImageableWidth;
            realImageableWidth = realImageableHeight;
            realImageableHeight = temp;
            
            // Effectue la rotation
            graphics.rotate(Math.PI / 2.0);
            // Translation pour s'aligner sur les marges
            graphics.translate(realImageableX, realImageableY - realImageableHeight);
            break;
         }
         default : {
            // Mode portrait
            // Translation pour s'aligner sur les marges
            graphics.translate(realImageableX, realImageableY);
         }
      }
      retValue.x      = (int)Math.ceil(realImageableX);
      retValue.y      = (int)Math.ceil(realImageableY);
      retValue.width  = (int)Math.floor(realImageableWidth);
      retValue.height = (int)Math.floor(realImageableHeight);
      
      return retValue;
   }
   
   /**
    * Affiche du texte indépendamment de la résolution
    *
    * @param graphics le contexte graphics
    * @param text le texte à afficher
    * @param x l'abscisse où placer le texte
    * @param y l'ordonnée où placer le texte
    */
   public void printText(Graphics2D graphics, String text, int x, int y){
      Font currentFont = graphics.getFont();
      
      // Calcul de l'échelle du texte
      double fontScale = ((double)(resolution*currentFont.getSize()))/(JAVA_DPI * 72.0);
      
      // Transformation de la police
      AffineTransform fontShapeTransform = new AffineTransform();
      fontShapeTransform.setToScale(fontScale, fontScale);
      
      // Font de récupération de glyph vector
      Font computeFont = new Font(currentFont.getName(), currentFont.getStyle(), 72);
      
      // Font de calcul de largeur d'un caractères => ignore le flag italique
      Font sizeFont = computeFont;
      if (font.isItalic()) {
         sizeFont = new Font(currentFont.getFontName(), currentFont.getStyle()-Font.ITALIC, 72);
      }
      
      // La position courante du texte
      Point2D.Double textPos = new Point2D.Double(x, y);
      
      // Récupère le contexte de rendu de police
      FontRenderContext frc = graphics.getFontRenderContext();
      
      // On boucle sur chaque caractères
      char[] carIterator = new char[1];
      int textLength = text.length();
      for (int i = 0; i < textLength; i++) {
         // récupère le caractère courant
         text.getChars(i, i+1, carIterator, 0);
         
         // Récupère le glyph de ce caractère pour la police de calcul
         GlyphVector glyph = computeFont.createGlyphVector(frc, carIterator);
         graphics.translate(textPos.x, textPos.y);
         glyph.setGlyphTransform(0, fontShapeTransform);
         graphics.drawGlyphVector(glyph, 0.f, 0.f);
         graphics.translate(-textPos.x, -textPos.y);
         
         // Incrémente la position
         TextLayout layout = new TextLayout(new String(carIterator), sizeFont, frc);
         textPos.x += (double)layout.getAdvance()* fontScale;
      }
   }
   
   /**
    * Affiche du texte indépendamment de la résolution
    *
    * @param graphics le contexte graphics
    * @param text le texte à afficher
    * @param x l'abscisse où placer le texte
    * @param y l'ordonnée où placer le texte
    */
   public void printPreviewText(Graphics2D graphics, String text, int x, int y, int printDpi, double screenScale){
      Font currentFont = graphics.getFont();
      int screenDpi = Toolkit.getDefaultToolkit().getScreenResolution();
      
      // Calcul l'échelle du texte
      double fontScale = ((double)(printDpi*currentFont.getSize()))/(double)(screenDpi * 72.0);
      
      // Transformation de la police
      AffineTransform fontShapeTransform = new AffineTransform();
      fontShapeTransform.setToScale(fontScale*screenScale, fontScale*screenScale);
      
      // Font de récupération de glyph vector
      Font computeFont = new Font(currentFont.getName(), currentFont.getStyle(), 72);
      
      // Font de calcul de largeur d'un caractères => ignore le flag italique
      Font sizeFont = computeFont;
      if (font.isItalic()) {
         sizeFont = new Font(currentFont.getFontName(), currentFont.getStyle()-Font.ITALIC, 72);
      }
      
      
      graphics.scale(1.0/screenScale,1.0/screenScale);
      
      // La position courante du texte
      Point2D.Double textPos = new Point2D.Double(x*screenScale, y*screenScale);
      
      // Récupère le contexte de rendu de police
      FontRenderContext frc = graphics.getFontRenderContext();
      
      
      // On boucle sur chaque caractère
      char[] carIterator = new char[1];
      int textLength = text.length();
      for (int i = 0; i < textLength; i++) {
         // Récupère le caractère courant
         text.getChars(i, i+1, carIterator, 0);
         
         // Récupère le glyph de ce caractère pour la police de calcul
         GlyphVector glyph = computeFont.createGlyphVector(frc, carIterator);
         glyph.setGlyphTransform(0, fontShapeTransform);
         graphics.translate(textPos.x, textPos.y);
         graphics.drawGlyphVector(glyph, 0.f, 0.f);
         graphics.translate(-textPos.x, -textPos.y);
         
         // Incrémente la position
         TextLayout layout = new TextLayout(new String(carIterator), sizeFont, frc);
         textPos.x += (double)layout.getAdvance()* fontScale*screenScale;
      }
      
      graphics.scale(screenScale,screenScale);
   }
   
   public void preview(Graphics2D graphics, Dimension screenSize, PrintParameters printParameters){
      
      
      // Taille de la feuille en pouce
      Rectangle2D.Double paperArea = printParameters.getPaperArea();
      
      // Convertit en pixels
      int printDpi = printParameters.getDPI();
      
      
      double paperAreaPixelsWidth = paperArea.width * printDpi;
      double paperAreaPixelsHeight = paperArea.height * printDpi;
      
      double xScaleRatio = screenSize.width / paperAreaPixelsWidth;
      double yScaleRatio = screenSize.height / paperAreaPixelsHeight;
      
      double projectionScale = xScaleRatio;
      if (yScaleRatio < xScaleRatio){
         projectionScale = yScaleRatio;
      }
      
      int screenPageWidth  = (int)(paperAreaPixelsWidth * projectionScale);
      int screenPageHeight = (int)(paperAreaPixelsHeight * projectionScale);
      
      int xScreenPageOffset = (screenSize.width - screenPageWidth)/2;
      int yScreenPageOffset = (screenSize.height - screenPageHeight)/2;
      
      
      // Dessine le fond du composant
      graphics.setColor(Color.DARK_GRAY);
      graphics.fillRect(0, 0, screenSize.width, screenSize.height);
      
      // Modifie la transformation
      graphics.translate(xScreenPageOffset, yScreenPageOffset);
      graphics.scale(projectionScale, projectionScale);
      
      
      // Dessine la feuille
      graphics.setColor(Color.WHITE);
      graphics.fillRect(0, 0, (int)paperAreaPixelsWidth, (int)paperAreaPixelsHeight);
      
      // Dessine les marges
      Rectangle2D.Double margin = printParameters.getPrintableArea();
      int xMargin      = (int)(margin.x * printDpi);
      int yMargin      = (int)(margin.y * printDpi);
      int widthMargin  = (int)(margin.width * printDpi);
      int heightMargin = (int)(margin.height * printDpi);
      
      graphics.setColor(Color.LIGHT_GRAY);
      graphics.drawRect(xMargin, yMargin,
              widthMargin, heightMargin);
      
      // Définit le clipping
      graphics.setClip(xMargin, yMargin,
              widthMargin, heightMargin);
      
      graphics.setColor(Color.BLACK);
      graphics.drawOval(xMargin, yMargin,
              widthMargin, heightMargin);
      
      Rectangle marg = new Rectangle(xMargin, yMargin,
              widthMargin, heightMargin);
      graphics.setFont(font);
      printPreviewText(graphics, marg.toString(),
              xMargin, yMargin+heightMargin/2, printDpi, projectionScale);
   }
   
   
   public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
      // Par défaut, retourne NO_SUCH_PAGE => la page n'existe pas
      int retValue = Printable.NO_SUCH_PAGE;
      if (pageIndex==0){
         Graphics2D g2d = (Graphics2D)graphics;
         // Restaure la résolution réelle
         Rectangle margin = restoreRealDpi(g2d, pageFormat);

         // Dessine le rectangle
         graphics.setColor(Color.BLACK);
         graphics.drawOval(0,
                 0,
                 margin.width,
                 margin.height);
         // Affiche les marges
         graphics.setFont(font);
         printText(g2d, margin.toString(), 0, margin.height/2);

         // La page est valide
         retValue = Printable.PAGE_EXISTS;
      }
      return retValue;
   }
}