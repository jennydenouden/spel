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
    //Zet de goede naam bij de huidige speler
    $.get("/getHuidigeSpeler", function (speler) {
        var naam = speler.name;
        console.log("speler : " + naam);
        $("#huidigeSpeler").text(naam);
    });


    //Handel de wisselbeurt knop af
    $("#wisselBeurt").click(function () {
        $.post("/wisselBeurt", undefined, function (speler) {
            var naam = speler.name;
            console.log("speler : " + naam);
            $("#huidigeSpeler").text(naam);
        })
    });


    //Teken bord
    var plaatjes = [];
    $.get("/tegelsOpBord", function (kolommen) {

        //Vul plaatjes met de locaties van de tegel pngs
        for (var i = 0; i < kolommen.length; i++) {
            plaatjes[i] = [];
            //console.log(kolommen[i]);
            for (var j = 0; j < kolommen[i].kolom.length; j++) {
                //console.log(kolommen[i].kolom[j].plaatje);
                plaatjes[i][j] = kolommen[i].kolom[j].plaatje;
            }
        }

        var canvas = document.getElementById('bordGrid');
        if (canvas.getContext) {

            var ctx = canvas.getContext('2d');

            var gridSize = 15;
            var tileSize = canvas.width / gridSize;

            for (var kolom = 0; kolom < gridSize; kolom++) {
                for (var rij = 0; rij < gridSize; rij++) {
                    //console.log("url: " + plaatjes[kolom][rij]);
                    var plaatje = imgMap.get(plaatjes[kolom][rij]);
                    var img = {plaatje: plaatje, x: kolom * tileSize, y: rij * tileSize};
                    img.x = (kolom * tileSize);
                    img.y = (rij * tileSize);
                    //img.plaatje.src = plaatjes[kolom][rij];
//                    ctx.strokeRect(kolom * tileSize, rij * tileSize, tileSize, tileSize);
                    ctx.drawImage(img.plaatje, img.x, img.y, tileSize, tileSize);

                }
            }


            $("#bordGrid").click(function (event) {
                //console.log("klik op (x: "+ (event.pageX - this.offsetLeft) + ", y: "+ (event.pageY - this.offsetTop ) + ")");
                var berekendeKolom = Math.min(Math.floor((event.pageX - this.offsetLeft) / (tileSize)), (gridSize - 1));
                var berekendeRij = Math.min(Math.floor((event.pageY - this.offsetTop) / (tileSize)), (gridSize - 1));
                //console.log("klik op het " + berekendeKolom + "e vakje van links, en " + berekendeRij + "e vakje van boven");

                $.post("/zetTegelOpBord", {kolom: berekendeKolom, rij: berekendeRij}, function (result) {
                    //teken het plaatje op de gegeven index opnieuw
                    if (result !== "") {
                        var plaatje = imgMap.get(result.plaatje);
                        var img = {plaatje: plaatje, x: berekendeKolom * tileSize, y: berekendeRij * tileSize};
                        img.x = (berekendeKolom * tileSize);
                        img.y = (berekendeRij * tileSize);
//                        ctx.strokeRect(img.x, img.y, tileSize, tileSize);
                        ctx.drawImage(img.plaatje, img.x, img.y, tileSize, tileSize);
                    }
                });
            });

        }


    });


}

//Preload de plaatjes en zet ze in een map, zodat ik ze kan uitlezen
function preload(arrayOfImages) {
    var imgMap = new Map();
    $(arrayOfImages).each(function () {
        var img = new Image();
        img.src = "/images/tegels/" + this;
        imgMap.set("/images/tegels/" + this, img);
    });

    return imgMap;
}

//Slaat alle preloaded images op in een map
var imgMap =
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
            "wwww.png",
            "leegvakje.jpg"]
                );