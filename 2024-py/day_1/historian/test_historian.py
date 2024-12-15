from ..historian.historian import Historian

hist = Historian("../data/example.txt")

def test_part1():
    assert 11 == hist.part1()

def test_part2():
    assert 31 == hist.part2()
