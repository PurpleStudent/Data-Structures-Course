import java.util.Arrays;
import java.util.Scanner;

public class RereadingTheText {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        String edges1 = scanner.nextLine();
        String edges2 = scanner.nextLine();


        Graph graph1 = new Graph(50000);          //  100000
        Graph graph2 = new Graph(5000);           //  10000


        while (!edges1.equals("")) {
            if (edges1.indexOf(')') + 1 < edges1.length()) {
                String edge = edges1.substring(0, edges1.indexOf(')') + 1);
                edges1 = edges1.substring(edges1.indexOf(')') + 1);
                add_edge_to_graph(graph1, edge);
            } else {
                String edge = edges1;
                edges1 = "";
                add_edge_to_graph(graph1, edge);
            }
        }

        while (!edges2.equals("")) {
            if (edges2.indexOf(')') + 1 < edges2.length()) {
                String edge = edges2.substring(0, edges2.indexOf(')') + 1);
                edges2 = edges2.substring(edges2.indexOf(')') + 1);
                add_edge_to_graph(graph2, edge);
            } else {
                String edge = edges2;
                edges2 = "";
                add_edge_to_graph(graph2, edge);
            }
        }


        PairsList pairsList = new PairsList();

        Node graph2_edge = graph2.getGraph()[1].head;
        //System.out.println("graph 2 edge  :     "     + graph2_edge.getEdge());
        //System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        for (int i = 0; i < graph1.getNumber_of_vertices() + 1; i++) {
            if (graph1.getGraph()[i] != null) {
                Node graph1_edge = graph1.getGraph()[i].head;
                while (graph1_edge != null) {


                    //System.out.println("graph 1 edge : "+"       "+graph1_edge.getEdge());


                    //System.out.println("......................................................................................................");


                    int plusDegree = graph1_edge.getEdge().angle - graph2_edge.getEdge().angle;
                    //System.out.println("plus degree =    "+ plusDegree);

                    boolean trueMap = true;

                    int[] map = new int[graph2.getNumber_of_vertices() + 1];
                    map[graph2_edge.getEdge().source] = graph1_edge.getEdge().source;

                    int[] color = new int[graph2.getNumber_of_vertices() + 1];   //0:سفید    1:خاکستری     2:مشکی
                    for (int j = 1; j < color.length; j++) {
                        color[j] = 0;
                    }
                    color[graph2_edge.getEdge().source] = 1;

                    //Queue<Integer> queue = new LinkedList<>();      //***
                    MyQueue queue= new MyQueue();
                    queue.add(graph2_edge.getEdge().source);

                    loop:
                    while (!queue.isEmpty()) {
                        //System.out.println(queue);
                        int u = queue.remove();

                        Node adj = graph2.getGraph()[u].head;
                        while (adj != null) {
                            //System.out.println("vertex "+u+"             adj edge :  "+adj.getEdge());

                            Edge edge;
                            if ((adj.getEdge().angle + plusDegree) % 360 > -1) {
                                edge = graph1.search_edge(map[adj.getEdge().source], (adj.getEdge().angle + plusDegree) % 360);
                            } else {
                                edge = graph1.search_edge(map[adj.getEdge().source], (360 + (adj.getEdge().angle + plusDegree) % 360));
                            }


                            //System.out.println("edge   :      "+edge);
                            if (edge == null) {
                                trueMap = false;
                                //System.out.println("break");
                                break loop;
                            } else {
                                if (color[adj.getEdge().destination] == 0) {
                                    map[adj.getEdge().destination] = edge.destination;
                                    color[adj.getEdge().destination] = 1;
                                    queue.add(adj.getEdge().destination);
                                }
                            }


                            adj = adj.getNext();
                        }
                        color[u] = 2;
                    }


                    if (trueMap) {
                        Pairs pairs = new Pairs(graph2.getNumber_of_vertices(), null);
                        for (int j = 0; j < graph2.getNumber_of_vertices(); j++) {
                            pairs.pairs[j] = new Pair(map[j + 1], j + 1);
                        }
                        bubbleSort(pairs.pairs, pairs.pairs.length);
                        pairsList.add(pairs);

                    }

                    //System.out.println(Arrays.toString(map));
                    //System.out.println("=======================================================================================================");

                    graph1_edge = graph1_edge.getNext();
                }
            }
        }


        //pairsList.printList();


        if (pairsList.lastPairs() != null) {
            for (int i = 0; i < pairsList.lastPairs().pairs.length; i++) {
                System.out.print(pairsList.lastPairs().pairs[i]);
            }
        } else {
            System.out.println("NOMATCH");
        }

    }


    static void add_edge_to_graph(Graph graph, String edge) {

        graph.setNumber_of_edges(graph.getNumber_of_edges() + 1);

        int source = Integer.parseInt(edge.substring(1, edge.indexOf(',')));
        String remainder1 = edge.substring(edge.indexOf(',') + 1);
        //-------------------------------------------------------------------------------------------------------------
        int destination = Integer.parseInt(remainder1.substring(0, remainder1.indexOf(',')));
        String remainder2 = remainder1.substring(remainder1.indexOf(',') + 1);
        //-------------------------------------------------------------------------------------------------------------
        int angle = Integer.parseInt(remainder2.substring(0, remainder2.indexOf(')')));


        if (graph.getNumber_of_vertices() < Math.max(source, destination)) {
            graph.setNumber_of_vertices(Math.max(source, destination));
        }


        Edge newEdge1 = new Edge(source, destination, angle);
        Node node1 = new Node(newEdge1, null);
        if (graph.getGraph()[source] == null) {
            graph.getGraph()[source] = new EdgeList();
            graph.getGraph()[source].insert(node1);
        } else {
            graph.getGraph()[source].insert(node1);
        }


        if (angle < 180) {
            Edge newEdge2 = new Edge(destination, source, angle + 180);
            Node node2 = new Node(newEdge2, null);

            if (graph.getGraph()[destination] == null) {
                graph.getGraph()[destination] = new EdgeList();
                graph.getGraph()[destination].insert(node2);
            } else {
                graph.getGraph()[destination].insert(node2);
            }
        } else {
            Edge newEdge2 = new Edge(destination, source, angle - 180);
            Node node2 = new Node(newEdge2, null);

            if (graph.getGraph()[destination] == null) {
                graph.getGraph()[destination] = new EdgeList();
                graph.getGraph()[destination].insert(node2);
            } else {
                graph.getGraph()[destination].insert(node2);
            }
        }
    }


    static void bubbleSort(Pair[] arr, int n) {
        int i, j;
        Pair temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j].graph1_vertex > arr[j + 1].graph1_vertex) {
                    // swap arr[j] and arr[j+1]
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // IF no two elements were swapped by inner loop, then break
            if (!swapped)
                break;
        }
    }
}


class Graph {

    private EdgeList[] graph;
    private int number_of_vertices;
    private int number_of_edges;

    public Graph(int max_vertices) {
        this.graph = new EdgeList[max_vertices];
        number_of_vertices = 0;
        number_of_edges = 0;
    }

    public EdgeList[] getGraph() {
        return graph;
    }

    public void setGraph(EdgeList[] graph) {
        this.graph = graph;
    }

    public int getNumber_of_vertices() {
        return number_of_vertices;
    }

    public void setNumber_of_vertices(int number_of_vertices) {
        this.number_of_vertices = number_of_vertices;
    }

    public int getNumber_of_edges() {
        return number_of_edges;
    }

    public void setNumber_of_edges(int number_of_edges) {
        this.number_of_edges = number_of_edges;
    }


    Edge search_edge(int source, int destination, int angle) {
        Edge edge = null;

        if (this.graph[source] != null) {
            edge = this.graph[source].search_edge(source, destination, angle);
        }

        return edge;
    }

    Edge search_edge(int source, int angle) {
        Edge edge = null;

        if (this.graph[source] != null) {
            edge = this.graph[source].search_edge(angle);
        }

        return edge;
    }

}


class Pair {

    int graph1_vertex;
    int graph2_vertex;


    public Pair(int graph1_vertex, int graph2_vertex) {
        this.graph1_vertex = graph1_vertex;
        this.graph2_vertex = graph2_vertex;
    }

    @Override
    public String toString() {
        return "(" + graph1_vertex + "," + graph2_vertex + ')';
    }
}


class Node {

    private Edge edge;
    private Node next;

    Node(Edge edge, Node next) {
        this.edge = edge;
        this.next = next;
    }

    public Edge getEdge() {
        return edge;
    }

    public void setEdge(Edge edge) {
        this.edge = edge;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}


class EdgeList {

    Node head;     // head of list


    public EdgeList() {
        this.head = null;
    }


    void insert(Node node) {
        // Create a new node with given data
        //Node new_node = new Node(data);
        //new_node.next = null;

        // If the Linked List is empty, then make the new node as head
        if (this.head == null) {
            this.head = node;
        } else {
            Node second = this.head;
            node.setNext(second);
            this.head = node;
        }
    }


    Edge search_edge(int source, int destination, int angle) {
        Edge edge = null;
        Node current = this.head;
        while (current != null) {
            if (current.getEdge().source == source
                    && current.getEdge().destination == destination
                    && current.getEdge().angle == angle) {
                edge = current.getEdge();
                break;
            }
            current = current.getNext();
        }
        return edge;
    }

    Edge search_edge(int angle) {
        Edge edge = null;
        Node current = this.head;
        while (current != null) {
            if (current.getEdge().angle == angle) {
                edge = current.getEdge();
                break;
            }
            current = current.getNext();
        }
        return edge;
    }


    void printList() {
        Node currentNode = this.head;

        System.out.print("List: ");

        // Traverse through the LinkedList
        while (currentNode != null) {
            // Print the data at current node
            System.out.print(currentNode.getEdge() + " ");

            // Go to next node
            currentNode = currentNode.getNext();
        }
    }

}


class Edge {
    int source;
    int destination;
    int angle;

    public Edge(int source, int destination, int angle) {
        this.source = source;
        this.destination = destination;
        this.angle = angle;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "source=" + source +
                ", destination=" + destination +
                ", angle=" + angle +
                '}';
    }
}
































class Pairs {

    Pair[] pairs;
    Pairs next;


    public Pairs(int pairs_number, Pairs next) {
        this.pairs = new Pair[pairs_number];
        this.next = next;
    }


    public Pairs getNext() {
        return next;
    }

    public void setNext(Pairs next) {
        this.next = next;
    }


    @Override
    public String toString() {
        return "Pairs{" +
                "pairs=" + Arrays.toString(pairs) +
                '}';
    }
}





























class PairsList {

    Pairs head;

    public PairsList() {
        this.head = null;
    }


    void add (Pairs pairs) {
        // Create a new node with given data
        //Node new_node = new Node(data);
        //new_node.next = null;

        // If the Linked List is empty, then make the new node as head
        if (this.head == null) {
            this.head = pairs;
        }
        else {
            Pairs second = this.head;
            pairs.setNext(second);
            this.head = pairs;
        }
    }



    void printList() {
        Pairs current = this.head;

        System.out.print("List: ");

        // Traverse through the LinkedList
        while (current != null) {
            // Print the data at current node
            System.out.print(current + " ");

            // Go to next node
            current = current.getNext();
        }
    }


    Pairs lastPairs(){
        Pairs current = this.head;

        if (current != null) {
            Pairs next = current.next;

            while (next != null) {
                current = next;
                next = current.next;
            }
        }

        return current;
    }

}





























class MyQueue {


    QueueNode front;

    public MyQueue() {
        this.front = null;
    }


    void add(int n){
        QueueNode node= new QueueNode(n,null);

        if (this.end() == null){
            this.front = node;
        }
        else {
            this.end().next = node;
        }
    }

    int remove(){
        int n= front.n;
        this.front = front.next;
        return n;
    }

    QueueNode end(){
        QueueNode current = this.front;

        if (current != null) {
            QueueNode next = current.next;

            while (next != null) {
                current = next;
                next = current.next;
            }
        }

        return current;
    }


    boolean isEmpty(){
        boolean b;
        b= this.front == null;
        return b;
    }
}





























class QueueNode {


    int n;
    QueueNode next;

    public QueueNode(int n, QueueNode next) {
        this.n = n;
        this.next = next;
    }
}