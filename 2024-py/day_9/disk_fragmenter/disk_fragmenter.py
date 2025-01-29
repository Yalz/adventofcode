import numpy as np
from numpy.dtypes import StringDType


class DiskFormatter:
	def __init__(self, disk_map: str):
		self.disk_map = []
		self.init_disk_map(disk_map)
		self.disk_map = np.array(self.disk_map, dtype=StringDType())

	def print(self):
		return ''.join(self.disk_map)

	def init_disk_map(self, disk_map: str):
		print()
		file_index = 0
		is_file = True

		for idx, indicator in enumerate([int(x) for x in list(disk_map)]):
			row = ''
			for i in range(indicator):
				if is_file:
					row += f'{file_index}'
				else:
					row += '.'
			self.disk_map.append(row)

			is_file = not is_file
			if is_file:
				file_index += 1



	def defrag(self):
		free_spaces = 0
		while (np.char.find(self.disk_map, '.') >= 0).any():

			empty_spots_idx = np.char.find(self.disk_map, '.').argmax()
			empty_spots = self.disk_map[empty_spots_idx]
			free_spots = self.disk_map[-1]

			if '.' in free_spots:
				if np.sum(np.char.find(self.disk_map, '.') != -1) > 1:
					free_spaces += free_spots.count('.')
					self.disk_map = np.delete(self.disk_map, -1, 0)
					continue
				else:
					break
			if len(free_spots) == 0:
				free_spaces += free_spots.count('.')
				self.disk_map = np.delete(self.disk_map, -1, 0)
				continue

			assigned_spots = min(empty_spots.count('.'), len(free_spots))

			self.disk_map[empty_spots_idx] = empty_spots.strip('.') + free_spots[0:assigned_spots] + ''.join(['.' for _ in range(empty_spots.count('.') - assigned_spots)])

			free_spaces += assigned_spots
			if len(free_spots) == assigned_spots:
				self.disk_map = np.delete(self.disk_map, -1, 0)
			else:
				self.disk_map[-1] = free_spots[assigned_spots:]

		self.disk_map = np.append(self.disk_map, ''.join(['.' for _ in range(free_spaces)]))

	def calculate_checksum(self):
		checksum = 0

		for idx, val in enumerate(list(map(int, self.print().strip('.')))):
			checksum += val * idx

		return checksum
