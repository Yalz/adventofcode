import numpy as np


def new_coordinate(coord, axis, change):
	new_coord = coord.copy()
	new_coord[axis] += change
	return new_coord


def filter_unique_start_end(array_list):
	unique_arrays = []
	seen_start_end = set()

	for array in array_list:
		start_point = tuple(array[0])
		end_point = tuple(array[-1])

		if (start_point, end_point) not in seen_start_end:
			unique_arrays.append(array)
			seen_start_end.add((start_point, end_point))

	return unique_arrays


class Trailhead:
	def __init__(self, grid):
		self.grid = np.array(grid)
		self.unexplored_paths = [[list(coord)] for coord in np.argwhere(self.grid == '0')]
		self.paths = []
		self.shape = self.grid.shape
		self.explore()

	def trailhead_score(self):
		unique_paths = filter_unique_start_end(self.paths)

		max_expected_paths = len([[list(coord)] for coord in np.argwhere(self.grid == '9')]) * len(
			[[list(coord)] for coord in np.argwhere(self.grid == '0')])
		assert len(unique_paths) <= max_expected_paths
		return len(unique_paths)

	def trailhead_rating(self):
		return len(self.paths)

	def explore(self):
		while len(self.unexplored_paths) > 0:
			path = self.unexplored_paths.pop()
			next_val = len(path)
			coord = path[-1]

			self.verify_cell(new_coordinate(coord, 0, 1), next_val, path)
			self.verify_cell(new_coordinate(coord, 0, -1), next_val, path)
			self.verify_cell(new_coordinate(coord, 1, 1), next_val, path)
			self.verify_cell(new_coordinate(coord, 1, -1), next_val, path)

	def verify_cell(self, coord, val, path):
		if coord[0] < 0 or coord[0] >= self.shape[0] or coord[1] < 0 or coord[1] >= self.shape[1]:
			return
		cell = self.grid[coord[0]][coord[1]]

		if cell == str(val):
			new_path = path.copy()
			new_path.append(coord)
			if len(path) == 9:
				self.paths.append(new_path)
			else:
				self.unexplored_paths.append(new_path)
