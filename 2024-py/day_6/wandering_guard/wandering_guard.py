import re
import numpy as np


def define_char(old_position, new_position, grid):
	if grid[new_position[0]][new_position[1]] in ['-', '|', '#', '+']:
		return '+'
	elif abs(old_position[0] - new_position[0]) == 1:
		return '|'
	elif abs(old_position[1] - new_position[1]) == 1:
		return '-'


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

	def turn(self):
		self.current_direction, self.next_direction = self.directions[self.next_direction]


def find_loop(current_position, grid):
	for i in range(1, 4):
		up = grid[:current_position[0], [current_position[1]]]
		down = grid[:current_position[0], [current_position[1]]]

class WanderingGuard:
	direction = Direction()

	def __init__(self, grid: [[]]):
		self.grid = np.array(grid)
		self.current_position = [0, 0]

	def find_start(self):
		for x, row in enumerate(self.grid):
			for match in re.finditer(Direction.directions_regex, ''.join(row)):
				self.direction.set_direction(match.group())
				self.current_position = [x, match.start()]
				if abs(self.direction.current_direction[0]) == 1:
					self.grid[self.current_position[0]][self.current_position[1]] = '|'
				else:
					self.grid[self.current_position[0]][self.current_position[1]] = '-'


	def wander(self):
		print()
		self.find_start()

		while True:
			old_position = self.current_position
			self.current_position = np.array(self.current_position) + np.array(self.direction.current_direction)
			if 0 <= self.current_position[0] < len(self.grid) and 0 <= self.current_position[1] < len(self.grid[0]):
				val = self.grid[self.current_position[0]][self.current_position[1]]
				if val == '#':
					self.current_position = old_position
					self.grid[self.current_position[0]][self.current_position[1]] = '+'
					self.direction.turn()
				else:
					find_loop(old_position, self.grid)
					dir_char = define_char(old_position, self.current_position, self.grid)
					self.grid[self.current_position[0]][self.current_position[1]] = dir_char

			else:
				print(self.grid)

				print()
				unique, counts = np.unique(self.grid, return_counts=True)
				occurrences = dict(zip(unique, counts))
				return (occurrences.get('X', 0) + occurrences.get('|', 0) + occurrences.get('-', 0) +
						occurrences.get('+', 0) + occurrences.get('^', 0))
