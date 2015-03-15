/*  1:   */ package com.iwinner.ws.servlet;
/*  2:   */ 
/*  3:   */ import com.sun.org.apache.xerces.internal.dom.DOMInputImpl;

/*  4:   */ import java.io.File;
/*  5:   */ import java.io.FileReader;
/*  6:   */ import java.io.IOException;
/*  7:   */ import java.io.InputStream;
/*  8:   */ import java.io.Reader;
/*  9:   */ import javax.xml.transform.Source;
/* 10:   */ import javax.xml.transform.sax.SAXResult;
/* 11:   */ import javax.xml.transform.sax.SAXSource;
/* 12:   */ import javax.xml.validation.Schema;
/* 13:   */ import javax.xml.validation.SchemaFactory;
/* 14:   */ import javax.xml.validation.Validator;
/* 15:   */ import org.w3c.dom.ls.LSInput;
/* 16:   */ import org.w3c.dom.ls.LSResourceResolver;
/* 17:   */ import org.xml.sax.InputSource;
/* 18:   */ import org.xml.sax.SAXException;
/* 19:   */ 
/* 20:   */ public class SchemaValidator
/* 21:   */   implements LSResourceResolver
/* 22:   */ {
/* 23:   */   private final String resourcePrefix;
/* 24:   */   
/* 25:   */   public SchemaValidator(String resourcePrefix)
/* 26:   */   {
/* 27:20 */     this.resourcePrefix = (resourcePrefix + "/");
/* 28:   */   }
/* 29:   */   
/* 30:   */   public SAXResult validate(Source xmlSource, String xmlSchema)
/* 31:   */     throws SAXException, IOException
/* 32:   */   {
/* 33:25 */     InputStream resourceAsStream = getClass().getResourceAsStream(this.resourcePrefix + xmlSchema);
/* 34:26 */     if (resourceAsStream == null) {
/* 35:28 */       throw new IOException("Cannot obtain resource '" + this.resourcePrefix + xmlSchema + "'");
/* 36:   */     }
/* 37:30 */     SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
/* 38:31 */     schemaFactory.setResourceResolver(this);
/* 39:32 */     Schema schema = schemaFactory.newSchema(new SAXSource(new InputSource(resourceAsStream)));
/* 40:   */     
/* 41:34 */     SAXResult result = new SAXResult();
/* 42:35 */     Validator validator = schema.newValidator();
/* 43:36 */     validator.setResourceResolver(this);
/* 44:37 */     validator.validate(xmlSource, result);
/* 45:   */     
/* 46:39 */     return result;
/* 47:   */   }
/* 48:   */   
/* 49:   */   public SAXResult validate(File xmlFile, String rootSchema)
/* 50:   */     throws IOException, SAXException
/* 51:   */   {
/* 52:44 */     FileReader reader = new FileReader(xmlFile);
/* 53:   */     try
/* 54:   */     {
/* 55:47 */       return validate(new SAXSource(new InputSource(reader)), rootSchema);
/* 56:   */     }
/* 57:   */     finally
/* 58:   */     {
/* 59:51 */       reader.close();
/* 60:   */     }
/* 61:   */   }
/* 62:   */   
/* 63:   */   public SAXResult validate(InputStream xmlFile, String rootSchema)
/* 64:   */     throws IOException, SAXException
/* 65:   */   {
/* 66:57 */     return validate(new SAXSource(new InputSource(xmlFile)), rootSchema);
/* 67:   */   }
/* 68:   */   
/* 69:   */   public SAXResult validate(Reader xmlFile, String rootSchema)
/* 70:   */     throws IOException, SAXException
/* 71:   */   {
/* 72:62 */     return validate(new SAXSource(new InputSource(xmlFile)), rootSchema);
/* 73:   */   }
/* 74:   */   
/* 75:   */   public LSInput resolveResource(String type, String namespaceURI, String publicId, String systemId, String baseURI)
/* 76:   */   {
/* 77:67 */     InputStream resourceAsStream = getClass().getResourceAsStream(this.resourcePrefix + systemId);
/* 78:68 */     return new DOMInputImpl(publicId, systemId, null, resourceAsStream, null);
/* 79:   */   }
/* 80:   */   
/* 81:   */   public String toString()
/* 82:   */   {
/* 83:74 */     return "SchemaValidator{resourcePrefix='" + this.resourcePrefix + '\'' + '}';
/* 84:   */   }
/* 85:   */ }


/* Location:           D:\Raghu_Folder\1.Ad-IT\Build\adselector_war.ear\adselector.war\WEB-INF\lib\AditAllJars\mobixell-utilities.jar
 * Qualified Name:     com.mobixell.util.schema.SchemaValidator
 * JD-Core Version:    0.7.0.1
 */