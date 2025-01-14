import copy
import re

import numpy as np


def define_char(old_position, new_position, grid):
	if grid[new_position[0]][new_position[1]] == '+':
		return '+'
	elif grid[new_position[0]][new_position[1]] in ['^']:
		return '^'
	elif abs(old_position[0] - new_position[0]) == 1:
		if grid[new_position[0]][new_position[1]] in ['|', 'x']:
			return grid[new_position[0]][new_position[1]]
		elif grid[new_position[0]][new_position[1]] == '-':
			return 'x'
		else:
			return '|'
	elif abs(old_position[1] - new_position[1]) == 1:
		if grid[new_position[0]][new_position[1]] in ['-', 'x']:
			return grid[new_position[0]][new_position[1]]
		elif grid[new_position[0]][new_position[1]] == '-':
			return 'x'
		else:
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
	alternate_grids = []

	def __init__(self, grid: [[]]):
		self.grid = np.array(grid)
		self.current_position = [0, 0]
		self.find_start()

	def find_start(self):
		for x, row in enumerate(self.grid):
			for match in re.finditer(Direction.directions_regex, ''.join(row)):
				self.direction.set_direction(match.group())
				self.current_position = [x, match.start()]

	def get_direct_line(self, current_position, direction):
		match direction:
			case [-1, 0]:
				return self.grid[:current_position[0], current_position[1]][::-1]
			case [1, 0]:
				return self.grid[current_position[0] + 1:, current_position[1]]
			case [0, -1]:
				return self.grid[current_position[0], :current_position[1]][::-1]
			case [0, 1]:
				return self.grid[current_position[0], current_position[1] + 1:]

	def find_loop(self, current_position):
		# Start: If, in next direction, it hits a #
		# Loop: Follow until hits +#
		# If hits End then drop
		direct_line = self.get_direct_line(current_position, Direction.directions[self.direction.next_direction][0])

		indices = np.where(direct_line == '#')[0]
		if indices.size > 0:
			alternate = copy.deepcopy(self)
			alternate.direction = copy.deepcopy(self.direction)
			alternate.grid = copy.deepcopy(self.grid)
			block_pos = np.add(current_position, alternate.direction.current_direction)
			alternate.grid[block_pos[0], block_pos[1]] = 'O'
			alternate.grid[current_position[0], current_position[1]] = '+'
			alternate.current_position = np.subtract(alternate.current_position, alternate.direction.current_direction)
			alternate.direction.turn()

			if alternate.wander(False):
				self.alternate_routes = alternate.alternate_routes + 1
				self.alternate_grids.append(alternate.grid)

	def wander(self, original=True):
		while True:
			old_position = self.current_position
			self.current_position = np.add(self.current_position, self.direction.current_direction)
			if 0 <= self.current_position[0] < len(self.grid) and 0 <= self.current_position[1] < len(self.grid[0]):
				val = self.grid[self.current_position[0]][self.current_position[1]]
				if val == '#':
					self.current_position = old_position
					self.grid[self.current_position[0]][self.current_position[1]] = '+'
					self.direction.turn()
				if val == '+':
					next_pos = np.array(self.current_position) + np.array(self.direction.current_direction)
					next_val = self.grid[next_pos[0]][next_pos[1]]
					if next_val == '#':
						return False
				else:
					if original:
						self.find_loop(old_position)
					dir_char = define_char(old_position, self.current_position, self.grid)
					self.grid[self.current_position[0]][self.current_position[1]] = dir_char
			else:
				return True

	def steps_taken(self):
		print()
		print(self.grid)

		print()
		unique, counts = np.unique(self.grid, return_counts=True)
		occurrences = dict(zip(unique, counts))
		return (occurrences.get('x', 0) + occurrences.get('|', 0) + occurrences.get('-', 0) +
				occurrences.get('+', 0) + occurrences.get('^', 0))