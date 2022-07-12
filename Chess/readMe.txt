README.txt


Setup File - (ex. testSetupFile.txt):
Format - Each piece should be entered on its own line and separated by a comma (,)
        (color),(piece name),(position)
blu,pawn,E2 - a blue pawn will be set on the grid at [6][4] the function will handle conversions


Colors accepted are blu and red
Pieces accepted are pawn, knight, bishop, rook, king, queen
        At least 1 piece will need to be placed on the board before the move file is accepted


Move File - (ex. testMoveFile.txt):
Format - Each move should be entered on its own line and separated by an equal sign (=)
        (beginning position)=(ending position)
E2=E4 - will select the piece on the grid at [6][4] and move to [4][4]


*the replay will not check for whether moves are accurate. If an inaccurate move is found the function will return without completing that move and try to continue on.