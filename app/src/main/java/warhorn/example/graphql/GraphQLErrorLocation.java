package warhorn.example.graphql;

public class GraphQLErrorLocation {
  private int column;
  private int line;

  public int getColumn() {
    return column;
  }

  public int getLine() {
    return line;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public void setLine(int line) {
    this.line = line;
  }
}
