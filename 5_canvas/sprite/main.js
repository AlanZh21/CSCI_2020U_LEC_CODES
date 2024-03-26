var canvas = document.querySelector('canvas');
var context = canvas.getContext('2d');

var spriteSheetURL = 'https://codehs.com/uploads/e4cfb06e001bd92cf41139928e88819a';

var image = new Image();
image.src = spriteSheetURL;
image.crossOrigin = true;

var position = spritePositionToImagePosition(1,0);
function animate() {
    // once we hit the end of a row,
    // move to the next
    if (col === 3) {
        col = 0;
        row += 1;
    }
    // once we finish the last row,
    // start again
    if (row === 2) {
        row = 0;
    }
    
    // make an image position using the 
    // current row and colum
    var position = spritePositionToImagePosition(row, col);
    context.clearRect(
        0,
        0,
        canvas.width,
        canvas.height
    );
    context.drawImage(
        image,
        position.x,
        position.y,
        SPRITE_WIDTH,
        SPRITE_HEIGHT,
        0,
        0,
        SPRITE_WIDTH,
        SPRITE_HEIGHT
    );
    col += 1;
}

image.onload = function(){
    setInterval(animate, 500)
}