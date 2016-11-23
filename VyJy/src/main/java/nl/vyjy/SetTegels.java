package nl.vyjy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/*
 * Deze klasse is alleen bedoeld om een verzameling terug te geven met alle bestaande tegels in het spel.
 */
public class SetTegels {

	/*
	 * For now een versimpelde lijst met alleen water en paadjes, en niet eens alle opties daaruit.
	 */
	public static ArrayList<Tegel> getAlleTegels() throws IOException{
		ArrayList<Tegel> alleTegels = new ArrayList<>();
		
		Tegel t;
                
//                for(image in directory){
                    t = new Tegel("/image/tegels/wwww.png");
                    // uiteindelijke format: img.src = '/images/tegels/wwww.png';
                    alleTegels.add(t);
                    t = new Tegel("/image/tegels/wwwl.png");
                    alleTegels.add(t);
//                }
	
		Collections.shuffle(alleTegels);
		return alleTegels;
        }
        
        // maakt een lijst van alle bestanden in "path" in string formaat
        public List<String> getResourceFiles( String path ) throws IOException {
            List<String> filenames = new ArrayList<>();

            try(
              InputStream in = getResourceAsStream( path );
              BufferedReader br = new BufferedReader( new InputStreamReader( in ) ) ) {
              String resource;

              while( (resource = br.readLine()) != null ) {
                filenames.add( resource );
              }
            }

            return filenames;
          }

          private InputStream getResourceAsStream( String resource ) {
            final InputStream in
              = getContextClassLoader().getResourceAsStream( resource );

            return in == null ? getClass().getResourceAsStream( resource ) : in;
          }

          private ClassLoader getContextClassLoader() {
            return Thread.currentThread().getContextClassLoader();
        }
}
