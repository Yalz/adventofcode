import copy

import numpy as np


class Antenna:
	anti_node_map = []
	anti_node_count = 0

	def __init__(self, freq_map):
		self.freq_map = np.array(freq_map)
		self.anti_node_map = copy.copy(self.freq_map)
		print()
		print("Antenna initialized")
		self.frequencies = np.unique(self.freq_map)
		self.frequencies = np.delete(self.frequencies, np.where(self.frequencies == '.'))
		self.generate_anti_node_amp()
		pass

	def assign_anti_node(self, an_spot):
		rows, cols = np.shape(self.freq_map)

		if 0 <= an_spot[0] < rows and 0 <= an_spot[1] < cols:
			self.anti_node_map[an_spot[0]][an_spot[1]] = '#'

	def generate_anti_node_amp(self):
		for frequency in self.frequencies:
			freq_positions = np.argwhere(self.freq_map == frequency)
			for freq_pos in freq_positions:

				for other_freq_pos in freq_positions:
					if np.array_equal(other_freq_pos,freq_pos):
						continue

					from_v = other_freq_pos - freq_pos
					an_spot = other_freq_pos + from_v
					self.assign_anti_node(an_spot)

					to_v = freq_pos - other_freq_pos
					an_spot = freq_pos + to_v

					self.assign_anti_node(an_spot)

		frequency, counts = np.unique(self.anti_node_map, return_counts=True)
		unique_counts_dict = dict(zip(frequency, counts))
		self.anti_node_count = unique_counts_dict['#']