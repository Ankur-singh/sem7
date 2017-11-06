from automatic.gameBoard import *

human = "X"
ai = "O"

def winning(board, player):
    if(board.board[0][0] == player and board.board[0][1] == player and board.board[0][2] == player) or\
        (board.board[1][0] == player and board.board[1][1] == player and board.board[1][2] == player) or\
        (board.board[2][0] == player and board.board[2][1] == player and board.board[2][2] == player) or\
        (board.board[0][0] == player and board.board[1][0] == player and board.board[2][0] == player) or\
        (board.board[0][1] == player and board.board[1][1] == player and board.board[2][1] == player) or\
        (board.board[0][2] == player and board.board[1][2] == player and board.board[2][2] == player) or\
        (board.board[0][0] == player and board.board[1][1] == player and board.board[2][2] == player) or\
        (board.board[0][2] == player and board.board[1][1] == player and board.board[2][0] == player) :
        return True
    else:
        return False


def minMax(board, player):
    #print player
    score = 0
    emptySpots = board.vacant()

    if winning(board, human):

        score = 10
        return score

    elif winning(board, ai):

        score = -10
        return score

    elif len(emptySpots) == 0:

        score = 0
        return score

    global moves

    for i in xrange(len(emptySpots)):
        move = {}
        score = 0
        row , col = emptySpots[i]
        move["index"] = (row, col)

        board.board[row][col] = player

        if player == human:
            result = minMax(board, ai)
            #print result
            score = result

        else:
            result = minMax(board, human)
            #print result
            score = result


        board.board[row][col] = "_"

        move["score"] = score

        moves.append(move)


    bestMove = 0

    if(player == ai):
        bestScore = -1000
        for i in xrange(len(moves)):
            if(moves[i]["score"] > bestScore):
                bestScore = moves[i]["score"]
                bestMove = i;

    else :
        bestScore = 1000
        for i in xrange(len(moves)):
            #print bestScore
            if (moves[i]["score"] < bestScore):
                bestScore = moves[i]["score"]
                bestMove = i;

    #print moves[bestMove]
    return bestMove

def playMove(tup, board, player):
    if player == human:
        print "human: "+str(tup)
        board.board[tup[0]][tup[1]] = player

    else:
        print "AI: " + str(tup)
        board.board[tup[0]][tup[1]] = player

def playTTT():
    global moves
    chance = 0
    count = 0

    while(count < 9):
        if chance == 1:
            a = minMax(b, ai)
        else:
            a = minMax(b, human)

        if (a == -10):
            print "AI won!!"
            break
        elif (a == 10):
            print "human won"
            break
        elif (a == 0 and len(moves) == 0):
            print "GAME OVER WITH A DRAW."
            break
        else:

            #print a
            #print moves

            if chance == 1:
                playMove(moves[a]["index"], b, ai)
                #moves.remove(a)
                b.display()
                chance = 0
            else:
                playMove(moves[a]["index"], b, human)
                #moves.remove(a)
                b.display()
                chance = 1
        #for m in moves:
        #    print m
        print "\n"
        del moves[:]
        count += 1

b = board()
b.display()

moves = []

playTTT()

'''
a= minMax(b, human)

if (a == 10):
    print "AI won!!"

elif (a == -10):
    print "human won"

elif (a == 0 and len(moves) == 0):
    print "GAME OVER WITH A DRAW."

else:

    print moves[a]
    playMove(moves[a]["index"], b, human)
    b.display()

'''