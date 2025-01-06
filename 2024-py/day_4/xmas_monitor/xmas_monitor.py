import re

from itertools import repeat


def find_word(lines: list[str], word):
    verify_grid(lines)

    times_found = 0
    # Horizontally
    for line in lines:
        times_found += len(re.findall(word, line))
        times_found += len(re.findall(word, line[::-1]))

    # Vertically
    t_grid = [list(row) for row in zip(*lines)]
    for line in t_grid:
        times_found += len(re.findall(word, "".join(line)))
        times_found += len(re.findall(word, "".join(line)[::-1]))

    for diagonal in diagonalOrder(lines):
        times_found += len(re.findall(word, "".join(diagonal)))
        times_found += len(re.findall(word, "".join(diagonal)[::-1]))


    print(diagonalOrder(lines))


    return times_found


def verify_grid(lines):
    vertical_count = len(lines)
    horizontal_count = len(lines[0])

    assert vertical_count == horizontal_count


def diagonalOrder(matrix):
    ROW = len(matrix)
    COL = len(matrix)

    diagonals = []

    # There will be ROW+COL-1 lines in the output
    for line in range(1, (ROW + COL)):
        # Get column index of the first element
        # in this line of output. The index is 0
        # for first ROW lines and line - ROW for
        # remaining lines
        start_col = max(0, line - ROW)

        # Get count of elements in this line.
        # The count of elements is equal to
        # minimum of line number, COL-start_col and ROW
        count = min(line, (COL - start_col), ROW)

        # Print elements of this line
        for j in range(0, count):
            diagonals.append(matrix[min(ROW, line) - j - 1] [start_col + j])

    return diagonals
