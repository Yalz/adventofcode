import copy
import math

import numpy as np


class Antenna:
	anti_node_map = []
	anti_node_count = 0


	def __init__(self, freq_map, ext=False):
		self.freq_map = np.array(freq_map)
		self.anti_node_map = copy.copy(self.freq_map)
		print()
		print("Antenna initialized")
		self.frequencies = np.unique(self.freq_map)
		self.frequencies = np.delete(self.frequencies, np.where(self.frequencies == '.'))
		self.frequencies = np.delete(self.frequencies, np.where(self.frequencies == '#'))
		self.rows, self.cols = np.shape(self.freq_map)
		self.generate_anti_node_amp(ext)
		pass

	def assign_anti_node(self, an_spot):
		if 0 <= an_spot[0] < self.rows and 0 <= an_spot[1] < self.cols:
			self.anti_node_map[an_spot[0]][an_spot[1]] = '#'

	def assign_anti_node_arr(self, start, direction):
		gcd_value = math.gcd(direction[0], direction[1])
		direction = direction // gcd_value
		while True:
			if 0 <= start[0] < self.rows and 0 <= start[1] < self.cols:
				self.anti_node_map[start[0]][start[1]] = '#'
				start = start + direction
			else:
				break
	def generate_anti_node_amp(self, ext=False):
		for frequency in self.frequencies:
			freq_positions = np.argwhere(self.freq_map == frequency)
			for freq_pos in freq_positions:

				for other_freq_pos in freq_positions:
					if np.array_equal(other_freq_pos,freq_pos):
						continue

					from_v = other_freq_pos - freq_pos
					an_spot = other_freq_pos + from_v
					self.assign_anti_node(an_spot)
					if ext:
						self.assign_anti_node_arr(other_freq_pos, from_v)

					to_v = freq_pos - other_freq_pos
					an_spot = freq_pos + to_v

					self.assign_anti_node(an_spot)
					if ext:
						self.assign_anti_node_arr(freq_pos, to_v)

		frequency, counts = np.unique(self.anti_node_map, return_counts=True)
		unique_counts_dict = dict(zip(frequency, counts))
		self.anti_node_count = unique_counts_dict['#']

