import re
from logging import debug

import numpy
import numpy as np


def parse_grid(lines: [str]):
    max_length = max(len(row) for row in lines)
    padded_lines = [row.ljust(max_length) for row in lines]
    return np.array([list(row) for row in padded_lines])


def find_xmas(lines: numpy.ndarray):
    word = "XMAS"
    times_found = 0
    # Horizontally
    for line in lines:
        times_found += len(re.findall(word, ''.join(line)))
        times_found += len(re.findall(word, ''.join(reversed(line))))

    print("Times found after horizontally", times_found)

    # Vertically
    t_grid = [list(row) for row in zip(*lines)]
    for line in t_grid:
        times_found += len(re.findall(word, ''.join(line)))
        times_found += len(re.findall(word, ''.join(reversed(line))))

    print("Times found after vertically", times_found)

    for diagonal in diagonals_of(lines):
        times_found += len(re.findall(word, ''.join(diagonal)))
        times_found += len(re.findall(word, ''.join(reversed(diagonal))))

    print("Times found after diagonally", times_found)

    return times_found


def diagonals_of(a: numpy.ndarray) -> [str]:
    diags = [a[::-1, :].diagonal(i) for i in range(-a.shape[0] + 1, a.shape[1])]
    diags.extend(a.diagonal(i) for i in range(a.shape[1] - 1, -a.shape[0], -1))

    diag_list = []
    for diag in diags:
        diag_list.append(diag.tolist())
    return diag_list


def find_mas_x(lines: np.ndarray):
    seq1 = "(?=(M.S))"
    seq2 = ".A."
    seq3 = "M.S"

    times_found = 0

    for _ in range(4):

        debug(lines)

        for idx, x in enumerate(lines):
            for match in re.finditer(seq1, ''.join(x)):
                if idx + 1 < len(lines):
                    substr = ''.join(lines[idx + 1])[match.start():match.start()+3]
                    for _ in re.finditer(seq2, substr):
                        if idx + 2 < len(lines):
                            substr = ''.join(lines[idx + 2])[match.start():match.start()+3]
                            for _ in re.finditer(seq3, substr):
                                times_found += 1
                                debug("Found MAS X at index", idx)
        debug("Rotating")
        lines = np.rot90(lines)

    return times_found


