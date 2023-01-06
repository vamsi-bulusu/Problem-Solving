#include <iostream>

class BST{
private:
    class node{
    public:
        char data;
        node *left;
        node *right;

        node(char ch) : data(ch), left(nullptr), right(nullptr) {}
    };
    node *root;//pointer to the root

    static void visit_inorder(const node*, void(*)(char)) ;
    static void insert(node*&n, char data);

public:
    BST() : root(nullptr) {}

    void insert(char data);

    // call a function for each element.
    void visit_inorder(void(*f)(char)) const { visit_inorder(root, f); }
};

void BST::insert(BST::node*& n, char data)
{
    if (data < n->data)
    {
        if (n->left)
            insert(n->left, data);
        else
            n->left = new node(data);
    }
    else if (data > n->data)
    {
        if (n->right)
            insert(n->right, data);
        else
            n->right = new node(data);
    }
}

void BST::insert(char data)
{
    if (root == nullptr)
        root = new node(data);
    else
        insert(root, data);
}

void BST::visit_inorder(const BST::node* n, void(*func)(char))
{
    if (!n)
        return;

    visit_inorder(n->left, func);
    func(n->data);
    visit_inorder(n->right, func);
}

void print_element(char element)
{
    std::cout << element << '\n';
}

int main()
{
    BST bst;
    bst.insert('P');
    bst.insert('B');
    bst.insert('Z');
    bst.insert('R');
    bst.insert('C');

    bst.visit_inorder(print_element);
}
