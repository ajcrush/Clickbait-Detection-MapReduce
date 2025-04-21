import matplotlib.pyplot as plt

# Step 1: Read the output file
with open('output.txt', 'r') as file:
    data = file.readlines()

# Step 2: Parse the data
words = []
counts = []

for line in data:
    line = line.strip()
    if not line:  # Skip empty lines
        continue
    parts = line.split()  # Split by space
    if len(parts) == 2:  # Ensure the line has both word and count
        word, count = parts
        words.append(word)
        counts.append(int(count))

# Step 3: Sort the data by counts in descending order (optional for better visualization)
sorted_data = sorted(zip(counts, words), reverse=True)
counts_sorted, words_sorted = zip(*sorted_data)

# Step 4: Create the bar chart
plt.figure(figsize=(10, 6))
plt.barh(words_sorted, counts_sorted, color='skyblue')
plt.xlabel('Frequency')
plt.ylabel('Words')
plt.title('Top Clickbait Words')
plt.show()
