package MyChess.Models.Figures;


public abstract class Figure  {
    private final ColorFigure color;
    public ColorFigure getColor(){
        return color;
    }



    public Figure(ColorFigure color){
        this.color=color;
    }
}
