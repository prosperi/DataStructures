var graph = {
	1: [
		[2, 1],
		[4, 2],
		[5, 3]
	],
	2: [
		[1, 1],
		[3, 3]
	],
	3: [
		[2, 3]
		[6, 1]
	],
	4: [
		[1, 2],
		[6, 3]
	],
	5: [
		[1, 3],
		[8, 1]
	],
	6: [
		[4, 3],
		[8, 2],
		[3, 1],
		[7, 1]
	],
	7: [
		[6, 1]
	],
	8: [
		[5, 1],
		[6, 2],
		[9, 1]
	],
	9: [
		[8, 1]
	]
};

TSP(<to_be_visited_list>, <current_path>, <current_node>, <current_travel_weight>){
	for(e in edges(for_current_node)){
		if(dest(e) in toBeVisited){

			pq.add(TSP(toBeVisited-dest(e), currentPathDest(e), dest(e), currentTotalWeight+weight(e)))
		}
	}
}
