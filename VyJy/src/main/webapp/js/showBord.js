function draw(){
    
	var plaatjes = [];
	$.get("/tegelsOpBord", function(kolommen){
		
		//Vul plaatjes met de locaties van de tegel pngs
		for(var i = 0; i < kolommen.length ; i++){	
			plaatjes[i] = [];
			//console.log(kolommen[i]);
			for(var j = 0 ; j < kolommen[i].kolom.length; j++){
				//console.log(kolommen[i].kolom[j].plaatje);
				plaatjes[i][j] = kolommen[i].kolom[j].plaatje;
			}
		}
		
		var canvas = document.getElementById('bordGrid');
	    if (canvas.getContext){
	        
	        var ctx = canvas.getContext('2d');
	        
	        var canvasWidth = canvas.width;
	        var canvasHeight = canvas.height;
	        var gridSize = 25;
	        var tileSize = canvasWidth / gridSize;
	        
	        for(var kolom = 0; kolom < gridSize; kolom++){
	        	for(var rij=0; rij < gridSize; rij++){     	
	        		var plaatje = new Image();
	        		var img = {plaatje : plaatje, x : kolom * tileSize, y : rij * tileSize};
	        		img.x = (kolom*tileSize);
	        		img.y = (rij * tileSize);
	        		img.plaatje.src = plaatjes[kolom][rij];
	        		//console.log("kolom: " + img.x + ", rij: " +img.y);
        			ctx.strokeRect(kolom*tileSize,rij*tileSize,tileSize,tileSize);
        			//console.log("kolom: " + img.x + ", rij: " +img.y);
        			ctx.drawImage(img.plaatje, img.x, img.y, tileSize, tileSize);

	        	}
	        }
	        
	        
	        $("#bordGrid").click(function(event){
				console.log("klik op (x: "+ event.clientX + ", y: "+ event.clientY + ")");
				var berekendeKolom = Math.floor((event.pageX - this.offsetLeft) / (tileSize+1));
				var berekendeRij = Math.floor((event.pageY - this.offsetTop) / (tileSize+1));
				console.log("klik op het " + berekendeKolom + "e vakje van links, en " + berekendeRij + "e vakje van boven");
				
				//hier moet je dan zo'n post methode doen dan wss
				
			});
	      
	    }
	    
		
	});
	
	
}

//Zou moeten werken volgens Reindert maar so far no luck
/*
function preload(arrayOfImages) {
    $(arrayOfImages).each(function(){
    	$('<img/>')[0].src = "/images/tegels/"+ this;
    });
}


preload(["bbbb.png",
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
	"filenames.txt",
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
	"tmp",
	"wwww - Copy.png",
	"wwww.png"]
);*/
