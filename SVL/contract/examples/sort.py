def sort(a):
    """Sort a list *IN PLACE*.

    >>> a = [1, 1, 1, 1, 1, 2, 2, 1]
    >>> sort(a)
    >>> a
    [1, 1, 1, 1, 1, 1, 2, 2]
    >>> a = 'the quick brown fox jumped over the lazy dog'.split()
    >>> sort(a)
    >>> a
    ['brown', 'dog', 'fox', 'jumped', 'lazy', 'over', 'quick', 'the', 'the']

    pre:
        # must be a list
        isinstance(a, list)

        # all elements must be comparable with all other items
        forall(range(len(a)),
               lambda i: forall(range(len(a)),
                                lambda j: (a[i] < a[j]) ^ (a[i] >= a[j])))

    post[a]:
        # length of array is unchanged
        len(a) == len(__old__.a)

        # all elements given are still in the array
        forall(__old__.a, lambda e: __old__.a.count(e) == a.count(e))

        # the array is sorted
        forall([a[i] >= a[i-1] for i in range(1, len(a))])
    """
    a.sort()

# enable contract checking
import contract
contract.checkmod(__name__)

def _test():
    import doctest, sort
    return doctest.testmod(sort)

if __name__ == "__main__":
    _test()
