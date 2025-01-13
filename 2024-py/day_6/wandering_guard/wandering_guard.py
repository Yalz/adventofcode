import re
from re import match

import numpy as np


def define_char(old_position, new_position, grid):
	if grid[new_position[0]][new_position[1]] in ['-', '|', 'x']:
		return 'x'
	elif grid[new_position[0]][new_position[1]] in ['#', '+']:
		return '+'
	elif grid[new_position[0]][new_position[1]] in ['^']:
		return '^'
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




class WanderingGuard:
	direction = Direction()
	alternate_routes = 0

	def __init__(self, grid: [[]]):
		self.grid = np.array(grid)
		self.current_position = [0, 0]

	def find_start(self):
		for x, row in enumerate(self.grid):
			for match in re.finditer(Direction.directions_regex, ''.join(row)):
				self.direction.set_direction(match.group())
				self.current_position = [x, match.start()]

	def find_loop(self, current_position):
		next_dir = Direction.directions[self.direction.next_direction][0]

		match next_dir:
			case [-1, 0]:
				sim = self.grid[:current_position[0], current_position[1]][::-1]
				self.find_for_line(sim, current_position)
			case [1, 0]:
				sim = self.grid[current_position[0] + 1:, current_position[1]]
				self.find_for_line(sim, current_position)
			case [0, -1]:
				sim = self.grid[current_position[0], :current_position[1]][::-1]
				self.find_for_line(sim, current_position)
			case [0, 1]:
				sim = self.grid[current_position[0], current_position[1] + 1:]
				self.find_for_line(sim, current_position)

	def find_for_line(self, line, current_position):
		indices = np.where(line == '#')[0]
		if indices.size > 0:
			index = indices[0]
			line = line[:index + 1]
			if len(line) >= 2 and line[-2] == '+':
				alternate = self.grid
				block_pos = np.add(current_position, self.direction.current_direction)
				alternate[block_pos[0], block_pos[1]] = 'O'

				self.alternate_routes = self.alternate_routes + 1



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
					self.find_loop(old_position)
					dir_char = define_char(old_position, self.current_position, self.grid)
					self.grid[self.current_position[0]][self.current_position[1]] = dir_char

			else:
				print(self.grid)

				print()
				unique, counts = np.unique(self.grid, return_counts=True)
				occurrences = dict(zip(unique, counts))
				return (occurrences.get('x', 0) + occurrences.get('|', 0) + occurrences.get('-', 0) +
						occurrences.get('+', 0) + occurrences.get('^', 0))
