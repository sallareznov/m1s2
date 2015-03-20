class circbuf:
    """A circular buffer of Python values.

    Examples::
        >>> cb = circbuf(3)
        >>> cb.is_empty()
        1
        >>> cb.put('first')
        >>> cb.is_empty()
        0
        >>> cb.put('second')
        >>> cb.put('third')
        >>> cb.is_full()
        1
        >>> cb.put('fourth')
        >>> cb.get()
        'second'
        >>> cb.get()
        'third'
        >>> cb.get()
        'fourth'
        >>> cb.is_empty()
        1

    inv::
        # there can be from 0 to max items inclusive on the buffer
        0 <= self.len <= len(self.buf)

        # g is a valid index into buf
        0 <= self.g < len(self.buf)

        # p is also a valid index into buf
        0 <= self.p < len(self.buf)

        # there are len items between get and put
        (self.p - self.g) % len(self.buf) == self.len % len(self.buf)
    """
    def __init__(self, leng):
        """Construct an empty circular buffer.

        pre::
            leng > 0
        post[self]::
            self.is_empty() and len(self.buf) == leng
        """

        self.buf = [None for x in range(leng)]
        self.len = self.g = self.p = 0

    def is_empty(self):
        """Returns true only if circbuf has no items.

        post[]::
            __return__ == (self.len == 0)
        """
        return self.len == 0

    def is_full(self):
        """Returns true only if circbuf has no space.

        post[]::
            __return__ == (self.len == len(self.buf))
        """
        return self.len == len(self.buf)

    def get(self):
        """Retrieves an item from a non-empty circular buffer.

        pre::
            not self.is_empty()
        post[self.len, self.g]::
            self.len == __old__.self.len - 1
            __return__ == self.buf[__old__.self.g]
        """
        result = self.buf[self.g]
        self.g = (self.g + 1) % len(self.buf)
        self.len -= 1
        return result

    def put(self, item):
        """Puts an item onto a circular buffer.

        post[self.len, self.p, self.g, self.buf]::
            # if the circbuf was full on entry, then an entry
            # was bumped
            implies(__old__.self.len == len(self.buf),
                    self.len == __old__.self.len,
                    self.len == __old__.self.len + 1 and \
                      self.g == __old__.self.g)

            # item is now in the buffer
            self.buf[__old__.self.p] == item
            # but no other part of the buffer has changed
            self.buf[:__old__.self.p] == __old__.self.buf[:__old__.self.p]
            self.buf[__old__.self.p+1:] == __old__.self.buf[__old__.self.p+1:]
            __return__ is None
        """
        self.buf[self.p] = item
        self.p = (self.p + 1) % len(self.buf)
        if self.len == len(self.buf):
            self.g = self.p
        else:
            self.len += 1


# enable contract checking
import contract
contract.checkmod(__name__)

def _test():
    import doctest, circbuf
    return doctest.testmod(circbuf)

if __name__ == '__main__':
    _test()
