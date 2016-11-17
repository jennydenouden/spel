function draw(){
    var canvas = document.getElementById('bordGrid');
    if (canvas.getContext){
        
        var ctx = canvas.getContext('2d');
        
        var canvasWidth = canvas.width;
        var canvasHeight = canvas.height;
        var gridSize = 25;
        var tileSize = canvasWidth / gridSize;
                             
        var img = new Image();
        img.onload = function(){
            var y = 0;
            for (x=0; x < canvasWidth; x++){
                for (y=0; y < canvasHeight; y++){
                    ctx.strokeRect(x*tileSize,y*tileSize,tileSize,tileSize);
                    // img = imgList[x][y]; // list from database
                    ctx.drawImage(img,x*tileSize,y*tileSize,tileSize,tileSize);
                }
            }
        };
        img.src = '/images/leegvakje.jpg';
    }
}