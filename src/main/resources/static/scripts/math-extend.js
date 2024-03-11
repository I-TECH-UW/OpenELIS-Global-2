// Math.extend v0.5.0

// Copyright (c) 2008-2009 Laurent Fortin
// 
// Permission is hereby granted, free of charge, to any person obtaining
// a copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to
// permit persons to whom the Software is furnished to do so, subject to
// the following conditions:
// 
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.


Object.extend(Math, {
    sum: function(list) {
        var sum = 0;
        
	for(var i = 0, len = list.length; i < len; i++)
	    sum += list[i];
	
        return sum;
    },
    
    mean: function(list) {
        return list.length ? this.sum(list) / list.length : false;
    },
    
    median: function(list) {
        if(!list.length) return false;
        
        list = this.sort(list);
        
        if(list.length.isEven()) {
            return this.mean([list[list.length / 2 - 1], list[list.length / 2]]);
        } else {
            return list[(list.length / 2).floor()];
        }
    },
    
    variance: function(list) {
        if(!list.length) return false;
        
        var mean = this.mean(list);
        var dev = [];
        
        for(var i = 0, len = list.length; i < len; i++)
	    dev.push(this.pow(list[i] - mean, 2));
        
        return this.mean(dev);
    },
    
    stdDev: function(list) {
    	return this.sqrt(this.variance(list));
    },
    
    sort: function(list, desc) {
        // we output a clone of the original array
        return list.clone().sort(function(a, b) { return desc ? b - a : a - b });
    },
    
    baseLog: function(n, base) {
        return this.log(n) / this.log(base || 10);
    },
    
    factorize: function(n) {
	if(!n.isNatural(true) || n == 1) return false;
	if(n.isPrime()) return [n];
	
    	var sqrtOfN = this.sqrt(n);
    	
        for(var i = 2; i <= sqrtOfN; i++)
            if((n % i).isNull() && i.isPrime())
                return [i, this.factorize(n / i)].flatten();
    },
    
    sinh: function(n) {
        return (this.exp(n) - this.exp(-n)) / 2;
    },
    
    cosh: function(n) {
        return (this.exp(n) + this.exp(-n)) / 2;
    },
    
    tanh: function(n) {
        return this.sinh(n) / this.cosh(n);
    }
});

Object.extend(Number.prototype, {
    isNaN: function() {
	return isNaN(this);
    },
    
    isNull: function() {
	return this == 0;
    },
    
    isEven: function() {
	if(!this.isInteger()) return false;
	
        return(this % 2 ? false : true);
    },
    
    isOdd: function() {
	if(!this.isInteger()) return false;
	
        return(this % 2 ? true : false);
    },
    
    isInteger: function(excludeZero) {
    	// if this == NaN ...
    	if(this.isNaN()) return false;
    	if(excludeZero && this.isNull()) return false;
    	
        return (this - this.floor()) ? false : true;
    },
    
    isNatural: function(excludeZero) {
	return(this.isInteger(excludeZero) && this >= 0);
    },
    
    isPrime: function() {
	var sqrtOfThis = Math.sqrt(this);
	var somePrimes = [2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101];
	
	if(!this.isNatural(true) || sqrtOfThis.isInteger()) {
	    return false;
	}
	if(somePrimes.include(this)) return true;
	
	for(var i = 0, len = somePrimes.length; i < len; i++) {
	    if(somePrimes[i] > sqrtOfThis) {
		return true;
	    }
	    if((this % somePrimes[i]).isNull()) {
		return false;
	    }
	}
	for(var i = 103; i <= sqrtOfThis; i += 2) {
	    if((this % i).isNull()) {
		return false;
	    }
	}
	
	return true;
    },
    
    compute: function(fn) {
        return fn(this);
    }
});

Object.extend(Array.prototype, {
    swap: function(index1, index2) {
        var swap = this[index1];
        
        this[index1] = this[index2];
        this[index2] = swap;
        
        return this;
    },
    
    shuffle: function(inline, times) {
        var list = (inline != false ? this : this.clone());
        
        for(var i = 0, len = list.length * (times || 4); i < len; i++) {
            list.swap(
		(Math.random() * list.length).floor(),
		(Math.random() * list.length).floor()
	    );
        }
        
        return list;
    },
    
    randomDraw: function(items) {
        items = Number(items) || 1;
        
        var list = this.shuffle(false);
        
        if (items >= list.length) {
            return list;
        }
        
        var sample = [];
        
        for(var i = 1; i <= items; i++) {
            if(list.length > 0) {
                sample.push(list.shift());
            } else {
                return sample;
            }
        }
        
        return sample;
    }
});

Object.extend(Object, {
    numericValues: function(object) {
        return Object.values(object).select(Object.isNumber);
    }
});

