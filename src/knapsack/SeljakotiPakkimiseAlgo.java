package knapsack;
//package ylesanne_3;
//
//public class SeljakotiPakkimiseAlgo {
//
//	
//	public void knapsack (int i, int profit, int weight) {
//		if (weight <= W && profit > maxprofit) {
//			maxprofit = profit;
//			numbest = i;
//			bestset = include;
//		}
//		if (promising(i)) {
//			include[i + 1] = "yes";
//			knapsack(i + 1, profit + p[i + 1], weight + w[i + 1]);
//			include[i + 1] = "no";
//			knapsack(i + 1, profit, weight);
//		}
//	}
//	
//	public boolean promising (int i) {
//		int j, k;
//		int totweight;
//		float bound;
//		
//		if (weight >= W) {
//			return false;
//		} else {
//			j = i + 1;
//			bound = profit;
//			totweight = weight;
//			while (j <= n && totweight + w[j] <= W) {
//				totweight = totweight + w[j];
//				bound = bound + p[j];
//				j++;
//			}
//			k = j;
//			if (k <= n) {
//				bound = bound + (W - totweight) * p[k]/w[k];
//			}
//			return bound > maxprofit;
//		}
//	}
//	
//	void laiutiPuuOtsing() {
//		
//		int breadth_first_search (tree T){ 
//			queue_of_node Q; 
//			node u, v, 
//			initialize(Q);  // Initialize Q to be empty.
//			v = root of T; 
//			visit v; // Visit root.
//			enqueue(Q, v); 
//			while (! empty(Q)){
//				dequeue(Q, v); 
//				for (each child u of v) { // Visit each child. 
//					visit u;
//					enqueue(Q, u); 
//				}
//			}
//	}
//		}
//		
//		void HjaKLaiutiPuuOtsing() {
//			
//			int breadth_first_branch_and_bound (state_space_tree T){ 
//				queue_of_node Q; 
//				node u, v, 
//				initialize(Q); // Initialize Q to be empty.
//				v = root of T; // Visit root.
//				best = value (v); 
//				enqueue(Q, v); 
//				while (! empty(Q)){ 
//					dequeue(Q, v); 
//					for (each child u of v) { // Visit each child. 
//						if (value(u) is better than best) // solution found
//							best = value (u); 
//						if (bound(u) is better than best) // promising child
//							enqueue(Q, u); 
//						}
//					}
//				return best
//				}
//		}
//		
//		
//		void s�gavutiRekusioonita() {
//			
//			int depth_first_branch_and_bound (state_space_tree T){
//				stack_of_node S; 
//				node u, v, 
//				initialize(S); // Initialize Q to be empty.
//				v = root of T; // Visit root.
//				best = value(v); 
//				push(S, v); 
//				while (! empty(S)){ 
//					pop(S, v); 
//					for (each child u of v) { // Visit each child. 
//						if (value(u) is better than best) // solution found
//							best = value (u); 
//						if (bound(u) is better than best) // promising child
//							push(S, u); 
//					}
//				}
//				return best
//				}
//		}
//			
//			
//		void HjaKparim-enneotsing() {
//			
//			int best_first_branch_and_bound (state_space_tree T){ 
//				priority_queue_of_node PQ; 
//				node u, v, 
//				initialize(PQ); // Initialize Q to be empty.
//				v = root of T;  // Visit root.
//				best = value(v); 
//				enqueue(PQ, v); 
//				while (! empty (PQ)){ 
////				Kontrolli uuesti, et tipp oleks ikka veel lootustandev
//					dequeue(PQ, v); 
//					if (bound(v) is worse than best) break;
//					for (each child u of v) { // Visit each child. 
//						if (value(u) is better than best) // solution found
//							best = value (u); 
//						if (bound(u) is better than best) // promising child
//							enqueue(PQ, u); 
//						}
//				}
//				return best }
//		}
//}
