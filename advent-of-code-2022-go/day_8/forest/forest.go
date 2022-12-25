package forest

import (
	"fmt"
	"sort"
	"strconv"
)

type FOREST map[string]int

var visibleTrees FOREST

func NewForest(treeLines []string) FOREST {
	visibleTrees = make(FOREST)

	for rowI, treeLine := range treeLines {
		ProcessHorizontalTreeLine(treeLine, rowI)

		var verticalLine string
		for j := 0; j < len(treeLines[rowI]); j++ {
			verticalLine += string(treeLines[j][rowI])
		}
		ProcessVerticalTreeLine(verticalLine, rowI)
	}

	return visibleTrees
}

func (f FOREST) TotalVisibleTrees() int {
	fmt.Println("Tree map:", f)
	return len(f)
}

func (f FOREST) TopScenicScore() int {
	keys := make([]string, 0, len(f))

	for key := range f {
		keys = append(keys, key)
	}

	sort.SliceStable(keys, func(i, j int) bool {
		return f[keys[i]] > f[keys[j]]
	})

	for _, k := range keys {
		fmt.Println(k, f[k])
	}

	return 0
}

func ProcessHorizontalTreeLine(treeline string, rowI int) {
	var highestTree, _ = strconv.Atoi(string(treeline[0]))
	for colI := 0; colI < len(treeline); colI++ {
		treeHeight, _ := strconv.Atoi(string(treeline[colI]))
		if colI == 0 {
			visibleTrees[strconv.Itoa(rowI)+","+strconv.Itoa(colI)] = highestTree
			continue
		}
		if highestTree < treeHeight {
			visibleTrees[strconv.Itoa(rowI)+","+strconv.Itoa(colI)] = treeHeight
			highestTree = treeHeight
		}
	}

	highestTree, _ = strconv.Atoi(string(treeline[len(treeline)-1]))
	for colI := len(treeline) - 1; 0 <= colI; colI-- {
		treeHeight, _ := strconv.Atoi(string(treeline[colI]))
		if colI == len(treeline)-1 {
			visibleTrees[strconv.Itoa(rowI)+","+strconv.Itoa(colI)] = highestTree
			continue
		}

		if highestTree < treeHeight {
			visibleTrees[strconv.Itoa(rowI)+","+strconv.Itoa(colI)] = treeHeight
			highestTree = treeHeight
		}
	}
}

func ProcessVerticalTreeLine(treeline string, colI int) {
	var highestTree, _ = strconv.Atoi(string(treeline[0]))
	for rowI := 0; rowI < len(treeline); rowI++ {
		treeHeight, _ := strconv.Atoi(string(treeline[rowI]))
		if rowI == 0 {
			visibleTrees[strconv.Itoa(rowI)+","+strconv.Itoa(colI)] = highestTree
			continue
		}
		if highestTree < treeHeight {
			visibleTrees[strconv.Itoa(rowI)+","+strconv.Itoa(colI)] = treeHeight
			highestTree = treeHeight
		}
	}
	highestTree, _ = strconv.Atoi(string(treeline[len(treeline)-1]))
	for rowI := len(treeline) - 1; 0 < rowI; rowI-- {
		treeHeight, _ := strconv.Atoi(string(treeline[rowI]))
		if rowI == len(treeline)-1 {
			visibleTrees[strconv.Itoa(rowI)+","+strconv.Itoa(colI)] = highestTree
			continue
		}
		if highestTree < treeHeight {
			visibleTrees[strconv.Itoa(rowI)+","+strconv.Itoa(colI)] = treeHeight
			highestTree = treeHeight
		}
	}
}
