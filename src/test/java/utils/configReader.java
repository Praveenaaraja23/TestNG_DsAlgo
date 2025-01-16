package utils;
   import java.io.BufferedReader;
	import java.io.FileNotFoundException;
	import java.io.FileReader;
	import java.io.IOException;
	import java.util.Properties;
	public class configReader {
		

	
		
		private static Properties properties;
		private final static String propertyFilePath = "src/test/resources/config/config.properties";
	
		
		public configReader() {
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(propertyFilePath));
				properties = new Properties();
				try {
					properties.load(reader);
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
			}

		}

		public static String getApplicationUrl() {
			String url = properties.getProperty("url");
			if (url != null)
				return url;
			else
				throw new RuntimeException("url not specified in the Configuration.properties file.");
		}
		public static String excelpath() {
			String path = properties.getProperty("path");
			if (path != null)
				return path;
			else
				throw new RuntimeException("url not specified in the Configuration.properties file.");
		
		
	}

	}






