### human = "X"
### ai = "O"

class board(object):
    def __init__(self):
        self.board = [["O","_","_"],
                      ["_","X","_"],
                      ["_","_","_"]]

    def vacant(self):
        vacantCell = []
        for i in xrange(3):
            for j in xrange(3):
                if self.board[i][j] == "_":
                    vacantCell.append((i,j))
        return vacantCell

    def display(self):
        print self.board[0]
        print self.board[1]
        print self.board[2]