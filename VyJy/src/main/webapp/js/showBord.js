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
            var x = 0;
            var y = 0;
            while (x < canvasWidth){
                while (y < canvasHeight){
                    ctx.strokeRect(x,y,tileSize,tileSize);
                    ctx.drawImage(img,x,y,tileSize,tileSize);
                    y += tileSize;
                }
                y = 0;
                x += tileSize; 
        }
        };
        img.src = '/images/leegvakje.jpg';
    }
}