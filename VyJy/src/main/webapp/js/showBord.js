function draw(){
    
	var plaatjes = [];
	$.get("/tegelsOpBord", function(kolommen){
		
		for(var i = 0; i < kolommen.length ; i++){	
			plaatjes[i] = [];
			//console.log(kolommen[i]);
			for(var j = 0 ; j < kolommen[i].kolom.length; j++){
				//console.log(kolommen[i].kolom[j].plaatje);
				plaatjes[i][j] = kolommen[i].kolom[j].plaatje;
			}
		}
		
		//console.log(plaatjes);
		
		var canvas = document.getElementById('bordGrid');
	    if (canvas.getContext){
	        
	        var ctx = canvas.getContext('2d');
	        
	        var canvasWidth = canvas.width;
	        var canvasHeight = canvas.height;
	        var gridSize = 25;
	        var tileSize = canvasWidth / gridSize;
	        
	        for(var kolom = 0; kolom < gridSize; kolom++){
	        	for(var rij=0; rij < gridSize; rij++){     		
	        		var img = {plaatje : new Image(), x : kolom * tileSize, y : rij * tileSize};
	        		img.x = (kolom*tileSize);
	        		img.y = (rij * tileSize);
	        		img.plaatje.src = plaatjes[kolom][rij];
	        		console.log("kolom: " + img.x + ", rij: " +img.y);
        			ctx.strokeRect(kolom*tileSize,rij*tileSize,tileSize,tileSize);
        			console.log("kolom: " + img.x + ", rij: " +img.y);
        			ctx.drawImage(img.plaatje, img.x, img.y, tileSize, tileSize);

	        	}
	        }
	      
	    }
		
	});
	
	
}