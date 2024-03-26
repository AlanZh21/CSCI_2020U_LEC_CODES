const SPRITE_WIDTH = 13;
const SPRITE_HEIGHT = 14;
const BORDER_WIDTH = 1;
const SPACE_WIDTH = 1;

function spritePositionToImagePosition(row,col){
    return{
        x:(
            BORDER_WIDTH + col*(SPACE_WIDTH + SPACE_WIDTH)
        ),
        y:(
            BORDER_WIDTH + row*(SPACE_WIDTH + SPRITE_HEIGHT)
        )
    }
}
