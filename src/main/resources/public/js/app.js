var gameModel;
var place = 0;

$( document ).ready(function() {
  // Handler for .ready() called.
  $.getJSON("model", function( json ) {
  gameModel = json;
    console.log( "JSON Data: " + json );
   });
});

function placeShip() {
   console.log($( "#shipSelec" ).val());
   console.log($( "#rowSelec" ).val());
   console.log($( "#colSelec" ).val());
   console.log($( "#orientationSelec" ).val());
   console.log("difficulty in place js " + $( "#difficultySelec" ).val());
   
   gameModel['difficulty'] = $( "#difficultySelec" ).val();

   //var menuId = $( "ul.nav" ).first().attr( "id" );
   var request = $.ajax({
     url: "/placeShip/"+$( "#shipSelec" ).val()+"/"+$( "#rowSelec" ).val()+"/"+$( "#colSelec" ).val()+"/"+$( "#orientationSelec" ).val(),
     method: "post",
     data: JSON.stringify(gameModel),
     contentType: "application/json; charset=utf-8",
     dataType: "json"
   });

   request.done(function( currModel ) {
     displayGameState(currModel);
     gameModel = currModel;

   });

   request.fail(function( jqXHR, textStatus ) {
     alert( "Request failed: " + textStatus );
   });

  place = 1;

}


function fire(){
  
   //check if plase ship
    if(place != 1){
        alert("You didn't place any ship!");
        return;
    }
  
 console.log($( "#rowFire" ).val());
 console.log($( "#colFire" ).val());
 gameModel['difficulty'] = $( "#difficultySelec" ).val();
//var menuId = $( "ul.nav" ).first().attr( "id" );
   var request = $.ajax({
     url: "/fire/"+$( "#rowFire" ).val()+"/"+$( "#colFire" ).val(),
     method: "post",
     data: JSON.stringify(gameModel),
     contentType: "application/json; charset=utf-8",
     dataType: "json"
   });

   request.done(function( currModel ) {
     displayGameState(currModel);
     gameModel = currModel;

   });

   request.fail(function( jqXHR, textStatus ) {
     alert( "Request failed: " + textStatus );
   });
}

function scan(){
$( '#TheirBoard td'  ).css("background-color", "red");
$( '#TheirBoard #' + 'hitbg'  ).css("background-color", "yellow");
$( '#TheirBoard #' +  'missbg'  ).css("background-color", "blue");
$( '#TheirBoard #' +  'scanbg'  ).css("background-color", "#ACFA58");
$( '#TheirBoard #' +  'failbg'  ).css("background-color", "#F8E0E0");
$( '#TheirBoard #' +  'same'  ).css("background-color", "#f0ffff");

    if(place != 1){
        alert("You didn't place any ship!");
        return;
    }
    var scanRow = $( "#rowFire" ).val();
    var scanCol = $( "#colFire" ).val();
    console.log("input " + scanRow);
    console.log("input " + scanCol);

    scanShip(scanRow, scanCol);
    scanShip(parseInt(scanRow)+1, scanCol);
    scanShip(scanRow - 1, scanCol);
    scanShip(scanRow, parseInt(scanCol)+1);
    scanShip(scanRow, scanCol - 1);

}


function log(logContents){
    console.log(logContents);
}

function displayGameState(gameModel){
$( '#MyBoard td'  ).css("background-color", "#7fff00");
$( '#TheirBoard td'  ).css("background-color", "red");
$( '#MyBoard #' + 'placed'  ).css("background-color", "yellow");
$( '#MyBoard #' +  'miss'  ).css("background-color", "green");
$( '#MyBoard #' +  'hit'  ).css("background-color", "red");
$( '#MyBoard #' +  'same'  ).css("background-color", "#f0ffff");

$( '#TheirBoard #' + 'hitbg'  ).css("background-color", "yellow");
$( '#TheirBoard #' +  'missbg'  ).css("background-color", "blue");
$( '#TheirBoard #' +  'scanbg'  ).css("background-color", "#ACFA58");
$( '#TheirBoard #' +  'failbg'  ).css("background-color", "#F8E0E0");
$( '#TheirBoard #' +  'same'  ).css("background-color", "#f0ffff");


displayShip(gameModel.aircraftCarrier);
displayShip(gameModel.battleship);
displayShip(gameModel.submarine);
displayShip(gameModel.clipper);
displayShip(gameModel.dinghy);

for (var i = 0; i < gameModel.computerMisses.length; i++) {
   $( '#TheirBoard #' + gameModel.computerMisses[i].Across + '_' + gameModel.computerMisses[i].Down ).css("background-color", "blue");
}
for (var i = 0; i < gameModel.computerHits.length; i++) {
   $( '#TheirBoard #' + gameModel.computerHits[i].Across + '_' + gameModel.computerHits[i].Down ).css("background-color", "yellow");
}

for (var i = 0; i < gameModel.playerMisses.length; i++) {
   $( '#MyBoard #' + gameModel.playerMisses[i].Across + '_' + gameModel.playerMisses[i].Down ).css("background-color", "green");
}
for (var i = 0; i < gameModel.playerHits.length; i++) {
   $( '#MyBoard #' + gameModel.playerHits[i].Across + '_' + gameModel.playerHits[i].Down ).css("background-color", "red");
}
}


function scanShip(row, col){
 console.log("row " + row);
 console.log("col " + col);
 startCoordAcross = gameModel.computer_aircraftCarrier.start.Across;
 startCoordDown = gameModel.computer_aircraftCarrier.start.Down;
 endCoordAcross = gameModel.computer_aircraftCarrier.end.Across;
 endCoordDown = gameModel.computer_aircraftCarrier.end.Down;

     if(startCoordAcross == endCoordAcross){        
         for (i = startCoordDown; i <= endCoordDown; i++) {
             if(startCoordAcross == row && i == col){
             $( '#TheirBoard #'+row+'_'+col  ).css("background-color", "#ACFA58");
             return;
             }
             else
             $( '#TheirBoard #'+row+'_'+col  ).css("background-color", "#F8E0E0");
         }
     } else {                                       
         for (i = startCoordAcross; i <= endCoordAcross; i++) {
             if(i == row && startCoordDown == col){
             $( '#TheirBoard #'+row+'_'+col  ).css("background-color", "#ACFA58");
             return;
             }
             else
             $( '#TheirBoard #'+row+'_'+col  ).css("background-color", "#F8E0E0");
         }
     }

 startCoordAcross = gameModel.computer_clipper.start.Across;
 startCoordDown = gameModel.computer_clipper.start.Down;
 endCoordAcross = gameModel.computer_clipper.end.Across;
 endCoordDown = gameModel.computer_clipper.end.Down;

     if(startCoordAcross == endCoordAcross){        //垂直
         for (i = startCoordDown; i <= endCoordDown; i++) {
             if(startCoordAcross == row && i == col){
             $( '#TheirBoard #'+row+'_'+col  ).css("background-color", "#ACFA58");
             return;
             }
             else
             $( '#TheirBoard #'+row+'_'+col  ).css("background-color", "#F8E0E0");
         }
     } else {                                       //水平
         for (i = startCoordAcross; i <= endCoordAcross; i++) {
             if(i == row && startCoordDown == col){
             $( '#TheirBoard #'+row+'_'+col  ).css("background-color", "#ACFA58");
             return;
             }
             else
             $( '#TheirBoard #'+row+'_'+col  ).css("background-color", "#F8E0E0");
         }
     }

 startCoordAcross = gameModel.computer_dinghy.start.Across;
 startCoordDown = gameModel.computer_dinghy.start.Down;
 endCoordAcross = gameModel.computer_dinghy.end.Across;
 endCoordDown = gameModel.computer_dinghy.end.Down;

     if(startCoordAcross == endCoordAcross){        //垂直
         for (i = startCoordDown; i <= endCoordDown; i++) {
             if(startCoordAcross == row && i == col){
             $( '#TheirBoard #'+row+'_'+col  ).css("background-color", "#ACFA58");
             return;
             }
             else
             $( '#TheirBoard #'+row+'_'+col  ).css("background-color", "#F8E0E0");
         }
     } else {                                       //水平
         for (i = startCoordAcross; i <= endCoordAcross; i++) {
             if(i == row && startCoordDown == col){
             $( '#TheirBoard #'+row+'_'+col  ).css("background-color", "#ACFA58");
             return;
             }
             else
             $( '#TheirBoard #'+row+'_'+col  ).css("background-color", "#F8E0E0");
         }
     }

}

function displayShip(ship){
 startCoordAcross = ship.start.Across;
 startCoordDown = ship.start.Down;
 endCoordAcross = ship.end.Across;
 endCoordDown = ship.end.Down;
// console.log(startCoordAcross);
 if(startCoordAcross > 0){
    if(startCoordAcross == endCoordAcross){
        for (i = startCoordDown; i <= endCoordDown; i++) {
            $( '#MyBoard #'+startCoordAcross+'_'+i  ).css("background-color", "yellow");
        }
    } else {
        for (i = startCoordAcross; i <= endCoordAcross; i++) {
            $( '#MyBoard #'+i+'_'+startCoordDown  ).css("background-color", "yellow");
        }
    }
 }
}
