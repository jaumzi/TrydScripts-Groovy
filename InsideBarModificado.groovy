def bars = BARS();
r = newLines();
r.add(bars);

def itens = bars.size();

for (int i = 1; i < itens; i++ ) {
    def currentCandle = bars.bar(i);

    // é candle de alta?
    if ( currentCandle.getClose() > currentCandle.getOpen() ) {
        currentCandle.setBorder(255, 255, 255);
        currentCandle.setFill(255, 255, 255);
    } else {                       
        currentCandle.setBorder(0, 0, 0);
        currentCandle.setFill(0, 0, 0);
    }
    
    // é InsideBar ?
    def previusCandle = bars.bar(i - 1);
    if ( previusCandle.getHigh() > currentCandle.getHigh() && previusCandle.getLow() < currentCandle.getLow() ) { 
        currentCandle.setFill(255, 255, 0);
        currentCandle.setBorder(255, 255, 0);
    } else {
        for (int j = i - 1; j > 1; j-- ) {
            def currentCandleInside = bars.bar(j);
            def previusCandleInside = bars.bar(j - 1);

            // anterior ao candle atual é um InsideBar
            if ( previusCandleInside.getHigh() > currentCandleInside.getHigh() && previusCandleInside.getLow() < currentCandleInside.getLow() ) { 
                // InsideBar foi rompido? espero que não
                if( currentCandleInside.getHigh() > currentCandle.getHigh() ) {
                    if (previusCandleInside.getHigh() > currentCandle.getHigh() && previusCandleInside.getLow() < currentCandle.getLow()) {
                        currentCandle.setFill(255, 255, 0);
                        currentCandle.setBorder(255, 255, 0);
                    }
                }
            }
        }
    }
}