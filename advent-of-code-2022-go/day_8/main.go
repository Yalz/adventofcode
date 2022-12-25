package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"

	"github.com/Yalz/adventofcode/forest"
)

func main() {
	forestOverview := forest.NewForest(ReadFile("/data/example.txt"))

	forestOverview.TopScenicScore()

	println("Total tree count : " + strconv.Itoa(forestOverview.TotalVisibleTrees()))
}

func ReadFile(path string) []string {
	pwd, _ := os.Getwd()
	f, err := os.Open(pwd + path)

	if err != nil {
		fmt.Println(err)
	}

	defer f.Close()

	scanner := bufio.NewScanner(f)
	scanner.Split(bufio.ScanWords)

	var treeSetup []string

	for scanner.Scan() {
		treeSetup = append(treeSetup, scanner.Text())
	}

	if err := scanner.Err(); err != nil {
		fmt.Println(err)
	}
	return treeSetup
}
