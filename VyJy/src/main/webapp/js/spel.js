function draw() {
    var canvas = document.getElementById("huidigeTegel");
    if (canvas.getContext) {
        canvas.height = canvas.width;
        var ctx = canvas.getContext("2d");
        $(document).ready(function () {
            $.get("/huidigeTegel", function (tegel) {
                console.log(tegel.plaatje);
                var plaatje = tegel.plaatje;
                var img = new Image();
                img.src = plaatje;
                img.onload = function () {
                    ctx.drawImage(img, 0, 0, img.width, img.height, // source rectangle
                            0, 0, canvas.width, canvas.height); // destination rectangle)
                }
            });
        });
    }
}