class MagicStones:
	def __init__(self, stones):
		self.stones = [int(item) for item in stones.split(' ')]
		self.mem = {}

	def roll(self):
		new_hand = []

		for stone in self.stones:
			stone_digit_length = len(str(stone))
			if stone == 0:
				new_hand.append(1)
			elif stone_digit_length % 2 == 0:
				split_idx = stone_digit_length // 2
				divisor = 10 ** (stone_digit_length - split_idx)

				new_hand.append(stone // divisor)
				new_hand.append(stone % divisor)
			else:
				new_hand.append(stone * 2024)

		self.stones = new_hand
		return self.stones

	def hand(self):
		return ' '.join(map(str, self.stones))

	def stone_count(self):
		return len(self.stones)
