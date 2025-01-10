import re
import numpy as np

class Direction:
    directions_regex = "<|>|v|\^"
    directions = {
        '^': [[-1, 0], '>'],
        'v': [[1, 0], '<'],
        '>': [[0, 1], 'v'],
        '<': [[0, -1], '^']
    }
    current_direction = []
    next_direction = ''

    def set_direction(self, direction):
        self.current_direction, self.next_direction = self.directions[direction]

    def get_direction(self):
        return self.current_direction

    def turn(self):
        self.current_direction, self.next_direction = self.directions[self.next_direction]

class WanderingGuard:
    direction = Direction()


    def __init__(self, grid: [[]]):
        self.grid = np.array(grid)
        self.current_position = [0,0]



    def find_start(self):
        for x, row in enumerate(self.grid):
            for match in re.finditer(Direction.directions_regex, ''.join(row)):
                self.direction.set_direction(match.group())
                self.current_position = [x, match.start()]

    def wander(self):
        self.find_start()

        while True:
            self.current_position = np.array(self.current_position) + np.array(self.direction.get_direction())
            if 0 <= self.current_position[0] < len(self.grid) and 0 <= self.current_position[1] < len(self.grid[0]):
                val = self.grid[self.current_position[0]][self.current_position[1]]
                if val == '#':
                    self.current_position = np.array(self.current_position) - np.array(self.direction.get_direction())
                    self.direction.turn()
                else:
                    self.grid[self.current_position[0]][self.current_position[1]] = 'X'
            else:
                print()
                print(self.grid)

                print()
                unique, counts = np.unique(self.grid, return_counts=True)
                return dict(zip(unique, counts)).get('X')