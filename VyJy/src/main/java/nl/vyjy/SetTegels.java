package nl.vyjy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/*
 * Deze klasse is alleen bedoeld om een verzameling terug te geven met alle bestaande tegels in het spel.
 */
public class SetTegels {

	/*
	 * Een lijst van tegels van alle plaatjes in de tegels map
         * Deze lijst is verkregen via:
         * cmd
         * dir /b >output.txt
	 */
	public static ArrayList<Tegel> getAlleTegels(){
            ArrayList<String> plaatjes = new ArrayList<>(Arrays.asList("bbbb.png",
                "bbbb_22.png",
                "bbbw - Copy (2).png",
                "bbbw - Copy.png",
                "bbbw.png",
                "bbbw_2.png",
                "bbbw_2b.png",
                "bbww_2 - Copy (2).png",
                "bbww_2 - Copy (3).png",
                "bbww_2 - Copy - Copy.png",
                "bbww_2 - Copy.png",
                "bbww_2.png",
                "bwbw_2 - Copy (2) - Copy.png",
                "bwbw_2 - Copy (2).png",
                "bwbw_2 - Copy (3).png",
                "bwbw_2 - Copy.png",
                "bwbw_2.png",
                "bwww - Copy.png",
                "bwww.png",
                "lbbw - Copy.png",
                "lbbw.png",
                "lblb - Copy.png",
                "lblb.png",
                "lblb_2.png",
                "lblw_2.png",
                "lbwb - Copy.png",
                "lbwb.png",
                "lbww - Copy.png",
                "lbww.png",
                "llbb_2_2 - Copy.png",
                "llbb_2_2.png",
                "llbb__2.png",
                "llbw_2.png",
                "lllb_3 - Copy.png",
                "lllb_3.png",
                "lllb_a.png",
                "lllb_b.png",
                "lllb_c.png",
                "llll.png",
                "llll_2.png",
                "llll_22 - Copy.png",
                "llll_3.png",
                "llll_4 - Copy.png",
                "llll_4.png",
                "lllw - Copy.png",
                "lllw.png",
                "lllw_2a.png",
                "lllw_2b.png",
                "lllw_3.png",
                "llwb_2.png",
                "llwb_a.png",
                "llwb_b.png",
                "llww - Copy.png",
                "llww.png",
                "llww_2 - Copy (2).png",
                "llww_2 - Copy.png",
                "llww_2.png",
                "lwbb - Copy.png",
                "lwbb.png",
                "lwbw - Copy.png",
                "lwbw.png",
                "lwlw - Copy (2).png",
                "lwlw - Copy (3).png",
                "lwlw - Copy.png",
                "lwlw.png",
                "lwwb - Copy.png",
                "lwwb.png",
                "lwww.png",
                "wwww - Copy.png",
                "wwww.png"));
            
            ArrayList<Tegel> alleTegels = new ArrayList<>();
            Tegel t;
            for(String plaatje: plaatjes){
                t = new Tegel(plaatje);
                alleTegels.add(t);
            }
	
		Collections.shuffle(alleTegels);
		return alleTegels;
        }
        
        // maakt een lijst van alle bestanden in "path" in string formaat
//        public List<String> getResourceFiles( String path ) throws IOException {
//            List<String> filenames = new ArrayList<>();
//
//            try(
//              InputStream in = getResourceAsStream( path );
//              BufferedReader br = new BufferedReader( new InputStreamReader( in ) ) ) {
//              String resource;
//
//              while( (resource = br.readLine()) != null ) {
//                filenames.add( resource );
//              }
//            }
//
//            return filenames;
//          }
//
//          private InputStream getResourceAsStream( String resource ) {
//            final InputStream in
//              = getContextClassLoader().getResourceAsStream( resource );
//
//            return in == null ? getClass().getResourceAsStream( resource ) : in;
//          }
//
//          private ClassLoader getContextClassLoader() {
//            return Thread.currentThread().getContextClassLoader();
//        }
}
