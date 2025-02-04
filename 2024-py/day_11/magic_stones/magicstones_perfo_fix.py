memory = {}

def roll_mem(stone, blinks):
	if blinks == 0:
		return 1
	elif (stone, blinks) in memory:
		return memory[(stone, blinks)]
	elif stone == 0:
		return roll_mem(1, blinks - 1)
	elif len(str_stone := str(stone)) % 2 == 0:
		split_idx = len(str_stone) // 2
		val = roll_mem(int(str_stone[:split_idx]), blinks - 1) + roll_mem(int(str_stone[split_idx:]), blinks - 1)
	else:
		val = roll_mem(stone * 2024, blinks - 1)
	memory[(stone, blinks)] = val
	return val