// Import the ND4J library
import org.deeplearning4j.nn.conf.layers.variational.LossFunctionWrapper;
import org.nd4j.linalg.api.buffer.DataType;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.Op;
import org.nd4j.linalg.api.ops.OpContext;
import org.nd4j.linalg.api.ops.impl.transforms.strict.Sigmoid;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;
public class Convergence {
    public static void main(String arg1, String arg2) {
        // Define the input and output variables
        INDArray X = Nd4j.create(new double[][] {{0, 0}, {0, 1}, {1, 0}, {1, 1}}); // Input features
        INDArray Y = Nd4j.create(new double[][] {{0}, {1}, {1}, {0}}); // Output labels

        // Define the network parameters
        int n_input = 2; // Number of input nodes
        int n_hidden = 4; // Number of hidden nodes
        int n_output = 1; // Number of output nodes
        double learning_rate = 0.1; // Learning rate
        double epsilon = 1e-6; // Convergence criterion
        int n_epochs = 1000; // Number of epochs

        // Define the weights and biases of the network
        INDArray W1 = Nd4j.randn(n_input, n_hidden); // Weights from input to hidden layer
        INDArray b1 = Nd4j.randn(1, n_hidden); // Biases from input to hidden layer
        INDArray W2 = Nd4j.randn(n_hidden, n_output); // Weights from hidden to output layer
        INDArray b2 = Nd4j.randn(1, n_output); // Biases from hidden to output layer

        // Define the model
        INDArray Z1 = X.mmul(W1).add(b1); // Linear transformation from input to hidden layer
        INDArray A1 = Nd4j.exec(new Sigmoid(Z1.dup())); // Sigmoid activation function from input to hidden layer
        INDArray Z2 = A1.mmul(W2).add(b2); // Linear transformation from hidden to output layer
        INDArray A2 = Nd4j.exec(new Sigmoid(Z2.dup())); // Sigmoid activation function from hidden to output layer

        // Define the cost function and optimizer
        INDArray cost = Nd4j.exec(new LossFunctionWrapper(LossFunctions.LossFunction.XENT, Y, A2, null, null)); // Binary cross-entropy
        Sgd optimizer = new Sgd(learning_rate); // Stochastic gradient descent

        // Define the accuracy metric
        INDArray pred = A2.gt(0.5); // Compare predictions with 0.5 threshold
        INDArray correct_pred = pred.eq(Y); // Compare predictions with labels
        double accuracy = correct_pred.castTo(Nd4j.defaultFloatingPointType()).meanNumber().doubleValue(); // Compute the mean accuracy

        // Define the convergence criterion
        boolean convergence = cost.meanNumber().doubleValue() <= epsilon; // Check if the cost is less than or equal to a small value

        // Create a loop to train the network
        for (int epoch = 0; epoch < n_epochs; epoch++) {
            // Print the cost and accuracy per epoch
            System.out.println("Epoch " + (epoch + 1) + ", Cost = " + cost.meanNumber() + ", Accuracy = " + accuracy);

            // Check the convergence
            if (convergence) {
                System.out.println("Convergence reached at epoch " + (epoch + 1));
                break;
            }

            // Compute the gradients of the weights and biases
            INDArray dZ2 = A2.sub(Y); // Derivative of cost with respect to Z2
            INDArray dW2 = A1.transpose().mmul(dZ2); // Derivative of cost with respect to W2
            INDArray db2 = dZ2.mean(0); // Derivative of cost with respect to b2
            INDArray dA1 = dZ2.mmul(W2.transpose()); // Derivative of cost with respect to A1
            INDArray dZ1 = dA1.mul(A1.rsub(1)).mul(A1); // Derivative of cost with respect to Z1
            INDArray dW1 = X.transpose().mmul(dZ1); // Derivative of cost with respect to W1
            INDArray db1 = dZ1.mean(0); // Derivative of cost with respect to b1

            // Update the weights and biases using the optimizer
            W1.subi(optimizer.getUpdater().getStateViewArray().assign(dW1)); // W1 = W1 - learning_rate * dW1
            b1.subi(optimizer.getUpdater().getStateViewArray().assign(db1)); // b1 = b1 - learning_rate * db1
            W2.subi(optimizer.getUpdater().getStateViewArray().assign(dW2)); // W2 = W2 - learning_rate * dW2
            b2.subi(optimizer.getUpdater().getStateViewArray().assign(db2)); // b2 = b2 - learning_rate * db2

            // Recompute the model, cost, accuracy and convergence
            Z1 = X.mmul(W1).add(b1);
            A1 = Nd4j.getExecutioner().execAndReturn(Nd4j.exec("sigmoid", Z1.dup()));
            Z2 = A1.mmul(W2).add(b2);
            A2 = Nd4j.getExecutioner().execAndReturn(Nd4j.exec("sigmoid", Z2.dup()));
            cost = Nd4j.getExecutioner().execAndReturn(Nd4j.getOpFactory().createLossFunction(LossFunctions.LossFunction.XENT, Y, A2));
            pred = A2.gt(0.5);
            correct_pred = pred.eq(Y);
            accuracy = correct_pred.castTo(Nd4j.defaultFloatingPointType()).meanNumber().doubleValue();
            convergence = cost.meanNumber().doubleValue() <= epsilon;
        }
    }
}
